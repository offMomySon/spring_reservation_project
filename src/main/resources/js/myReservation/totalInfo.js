class Builder{
    constructor(){
        this.email = document.location.search.split("=")[1];
        this.confirmRsv = document.querySelector(".card.confirmed");
        this.cancelRsv = document.querySelector(".card.used.cancel");
        this.rsvInfo = null;
    }

    static get RSVINFOURL(){
        return `/reservation/api/reservations`;
    }

    static async create(){
        let buildIns = new Builder();
        buildIns.rsvInfo = await buildIns.getRsvInfo();
        console.log(JSON.stringify(buildIns.rsvInfo));

        buildIns.makePage();
        buildIns.addCBtoCancleBtn();

        return buildIns;
    }

    getRsvInfo(){
        return new Promise( (resolve) =>{
            var httpRequest = new XMLHttpRequest();
            httpRequest.addEventListener("load",()=>{
                resolve(JSON.parse(httpRequest.responseText));
            });
            httpRequest.open('GET', Builder.RSVINFOURL +'?reservationEmail='+ this.email);
            httpRequest.send();
        })
    }

    makePage(){
        let confirmTicketInfo = this.getConFirmTicketInfo();
        let cancleTicketInfo = this.getCancleTicketInfo();

        let confirmHtml = this.makeTicket(confirmTicketInfo);
        let cancleHtml = this.makeTicket(cancleTicketInfo);

        this.confirmRsv.insertAdjacentHTML('beforeend', confirmHtml);
        this.cancelRsv.insertAdjacentHTML('beforeend', cancleHtml);
    }

    getConFirmTicketInfo(){
        let reservationArr = this.rsvInfo["reservations"];
        let confirmTicketInfo = { 'reservations' : [] };

        reservationArr.forEach((elm)=>{
            if(elm['cancelYn'] === false){
                confirmTicketInfo['reservations'].push(elm);
            }
        });

        return confirmTicketInfo;
    }
    getCancleTicketInfo(){
        let reservationArr = this.rsvInfo["reservations"];
        let cancleicketInfo = { 'reservations' : [] };

        reservationArr.forEach((elm)=>{
            if(elm['cancelYn'] === true){
                cancleicketInfo['reservations'].push(elm);
            }
        });

        return cancleicketInfo;
    }

    makeTicket(rsvInfo){
        let template = document.querySelector("#rsvConfirmed").innerHTML;
        let ticketInfoFactory = Handlebars.compile(template);

        return ticketInfoFactory(rsvInfo);
    }

    addCBtoCancleBtn(){
        let allCancleBtn = document.querySelectorAll('.btn');

        Array.from(allCancleBtn).forEach((elm)=>{
            elm.addEventListener('click',this.cancleTicketIfNeeded.bind(this));
        });
    }

    async cancleTicketIfNeeded(evt){
        evt.preventDefault();

        const message = "취소하시겠습니까?";
        let result = window.confirm(message);
        if( result === false ){
            return;
        }

        let requestResult = null;
        let rsvId = parseInt(evt.currentTarget.dataset.ticketId);
        try{
            requestResult = await this.requestRsvCancle(rsvId);
        }
        catch(err){
            console.log(err);
            requestResult = false;
        }

        if(requestResult){
            document.querySelector('.card.used.cancel').appendChild(document.querySelector('#rsv_'+rsvId)); 
        }
    }

    requestRsvCancle(rsvId){
        return new Promise( (resolve) =>{
            var httpRequest = new XMLHttpRequest();
            httpRequest.addEventListener("load",()=>{
                if(httpRequest.status != 200){
                    resolve(false);
                }

                if( JSON.parse(httpRequest.responseText).prices <= 0){
                    resolve(false);
                }
                
                resolve(true);
            });
            httpRequest.addEventListener("error",()=>{
                throw new Error("404");
            });
            
            httpRequest.open('PUT', Builder.RSVINFOURL +`/${rsvId}`);
            httpRequest.send();
        })
    }
}

Handlebars.registerHelper('RsvId', (rsvId) => {
    const rsvNoLength = 7;
    rsvId = rsvId+'';
    
    for(let idx = 0; idx< rsvNoLength - rsvId.length; idx++){
        rsvId = '0' + rsvId;
    }

    return 'No.' + rsvId;
});

Handlebars.registerHelper('Price', (price) => {
    let localePrice = parseInt(price).toLocaleString();
    return `${localePrice}`;
});

Handlebars.registerHelper('CancleBtn', (rsvId, cancelYn) => {
    if(cancelYn  === true){
        return "";
    }

    return `<div class="booking_cancel"> <button class="btn" data-ticket-id="${rsvId}"><span>취소</span></button> </div>`;
});

// summary board class 생성.

// 빌드 class 가 필요.
//     server 를 통해 각각의 category 에 예약정보 build 후 category 에 분배.
//     예약 category 에 있는 버튼들에 대해 취소요청하는 callback 함수 등록
//     response 값이 적절하게 return 되면, categorymanager 에 id 를 넘겨주고 이동해달라고 요청

// categorymanager  class 생성. 
// 취소시 기존카테고리에서 취소 카테고리로 이동 함수 필요.

// 취소 카테고리에서 id 별로 sort 하는 함수 필요.





