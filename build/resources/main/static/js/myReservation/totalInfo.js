class Builder {
    constructor() {
        this.email = document.location.search.split("=")[1];
        this.confirmRsv = document.querySelector(".card.confirmed");
        this.cancelRsv = document.querySelector(".card.used.cancel");

        let tempItemCountList = document.querySelectorAll('.figure');
        this.totalCount = tempItemCountList[0];
        this.confirmedCount = tempItemCountList[1];
        this.usedCount = tempItemCountList[2];
        this.cancleCount = tempItemCountList[3];

        this.rsvInfo = null;
    }

    static get RSVINFOURL() {
        return `/api/reservations`;
    }

    static async create() {
        let buildIns = new Builder();
        buildIns.rsvInfo = await buildIns.getRsvInfo();
        console.log(JSON.stringify(buildIns.rsvInfo));

        buildIns.makePage();
        buildIns.addCBtoCancleBtn();

        return buildIns;
    }

    getRsvInfo() {
        return new Promise((resolve) => {
            var httpRequest = new XMLHttpRequest();
            httpRequest.addEventListener("load", () => {
                resolve(JSON.parse(httpRequest.responseText));
            });
            httpRequest.open('GET', Builder.RSVINFOURL + '?reservationEmail=' + this.email);
            httpRequest.send();
        })
    }

    makePage() {
        this.makeTicket();
        this.makeCountTab();
    }

    makeTicket() {
        let confirmTicketInfo = this.getConFirmTicketInfo();
        let cancleTicketInfo = this.getCancleTicketInfo();

        let confirmHtml = this.makeTicketHtml(confirmTicketInfo);
        let cancleHtml = this.makeTicketHtml(cancleTicketInfo);

        this.confirmRsv.insertAdjacentHTML('beforeend', confirmHtml);
        this.cancelRsv.insertAdjacentHTML('beforeend', cancleHtml);
    }

    makeCountTab() {
        this.totalCount.innerHTML = this.getConFirmTicketCount() + this.getCancelTicketCount();
        this.confirmedCount.innerHTML = this.getConFirmTicketCount();
        this.cancleCount.innerHTML = this.getCancelTicketCount();
    }

    getConFirmTicketCount() {
        let reservationArr = this.rsvInfo["reservations"];
        let count = 0;

        reservationArr.forEach((rsv) => {
            if (rsv['cancelYn'] === false) {
                count += 1;
            }
        });

        return count;
    }

    getCancelTicketCount() {
        let reservationArr = this.rsvInfo["reservations"];
        let count = 0;

        reservationArr.forEach((rsv) => {
            if (rsv['cancelYn'] === true) {
                count += 1;
            }
        });

        return count;
    }

    getConFirmTicketInfo() {
        let reservationArr = this.rsvInfo["reservations"];
        let confirmTicketInfo = { 'reservations': [] };

        reservationArr.forEach((elm) => {
            if (elm['cancelYn'] === false) {
                confirmTicketInfo['reservations'].push(elm);
            }
        });

        return confirmTicketInfo;
    }
    getCancleTicketInfo() {
        let reservationArr = this.rsvInfo["reservations"];
        let cancleicketInfo = { 'reservations': [] };

        reservationArr.forEach((elm) => {
            if (elm['cancelYn'] === true) {
                cancleicketInfo['reservations'].push(elm);
            }
        });

        return cancleicketInfo;
    }

    makeTicketHtml(rsvInfo) {
        let template = document.querySelector("#rsvConfirmed").innerHTML;
        let ticketInfoFactory = Handlebars.compile(template);

        return ticketInfoFactory(rsvInfo);
    }

    addCBtoCancleBtn() {
        let allCancleBtn = document.querySelectorAll('.btn');

        Array.from(allCancleBtn).forEach((elm) => {
            elm.addEventListener('click', this.cancleTicketIfNeeded.bind(this));
        });
    }

    async cancleTicketIfNeeded(evt) {
        evt.preventDefault();

        const message = "취소하시겠습니까?";
        let result = window.confirm(message);
        if (result === false) {
            return;
        }

        let requestResult = null;
        let rsvId = parseInt(evt.currentTarget.dataset.ticketId);
        try {
            requestResult = await this.requestRsvCancle(rsvId);
        }
        catch (err) {
            console.log(err);
            requestResult = false;
        }

        if (requestResult) {
            this.moveTicketToCancle(rsvId);
            this.changeRsvCountAtTab();
        }
    }

    moveTicketToCancle(rsvId) {
        document.querySelector('.card.used.cancel').appendChild(document.querySelector('#rsv_' + rsvId));
    }

    changeRsvCountAtTab() {
        this.confirmedCount.innerHTML = parseInt(this.confirmedCount.innerHTML) - 1;
        this.cancleCount.innerHTML = parseInt(this.cancleCount.innerHTML) + 1;
    }

    requestRsvCancle(rsvId) {
        return new Promise((resolve) => {
            var httpRequest = new XMLHttpRequest();
            httpRequest.addEventListener("load", () => {
                if (httpRequest.status != 200) {
                    resolve(false);
                }

                if (JSON.parse(httpRequest.responseText).prices <= 0) {
                    resolve(false);
                }

                resolve(true);
            });
            httpRequest.addEventListener("error", () => {
                throw new Error("404");
            });

            httpRequest.open('PUT', Builder.RSVINFOURL + `/${rsvId}`);
            httpRequest.send();
        })
    }
}

Handlebars.registerHelper('RsvId', (rsvId) => {
    const rsvNoLength = 7;
    rsvId = rsvId + '';

    for (let idx = 0; idx < rsvNoLength - rsvId.length; idx++) {
        rsvId = '0' + rsvId;
    }

    return 'No.' + rsvId;
});

Handlebars.registerHelper('Price', (price) => {
    let localePrice = parseInt(price).toLocaleString();
    return `${localePrice}`;
});

Handlebars.registerHelper('CancleBtn', (rsvId, cancelYn) => {
    if (cancelYn === true) {
        return "";
    }

    return `<div class="booking_cancel"> <button class="btn" data-ticket-id="${rsvId}"><span>취소</span></button> </div>`;
});