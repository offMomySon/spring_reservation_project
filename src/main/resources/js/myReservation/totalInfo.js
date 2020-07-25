class Server{
    constructor(){
        this.email = document.location.search.split("=")[1];
        this.rsvInfo = null;
    }

    static get RSVINFOURL(){
        return `/reservation/api/reservations`;
    }

    static async create(){
        let srv = new Server();
        srv.rsvInfo = await srv.getRsvInfo();
        console.log(srv.rsvInfo);

        return srv;
    }

    getRsvInfo(){
        return new Promise( (resolve) =>{
            var httpRequest = new XMLHttpRequest();
            httpRequest.addEventListener("load",()=>{
                resolve(JSON.parse(httpRequest.responseText));
            });
            httpRequest.open('GET', Server.RSVINFOURL +'?reservationEmail='+ this.email);
            httpRequest.send();
        })
    }

}
