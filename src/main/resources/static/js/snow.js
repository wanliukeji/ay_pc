//    窗口大小
var oPageX = window.screen.width;
var oPageY = window.screen.height;
var aSnow = [];

//    雪花模板
function snow(x, y, xspeed, yspeed, xsin, size) {//x水平位置,y垂直位置,xspeed水平步距,yspeed垂直步距,xsin振幅,size雪花大小
    this.node = document.createElement('div');
    this.x = x;
    this.y = y;
    this.xspeed = xspeed;
    this.yspeed = yspeed;
    this.xsin = xsin;
    this.size = size;
    this.createSnow = function () {
        this.node.style.position = 'absolute';
        this.node.style.top = this.y + 'px';
        this.node.style.left = this.x + 'px';
        var w = Math.floor(Math.random() * 60 + 10);
        // this.node.innerHTML = '*';
        this.node.innerHTML = '<svg t="1571355764310" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1667" width="' + w + '" height="20"><path d="M496 30.4L472 72v880l24 41.6 24-41.6V72z" fill="#ffffff" p-id="1668"></path><path d="M496 240l-96-64v-48l96 64zM496 336L368 256v-48l128 80zM496 240l96-64v-48l-96 64zM496 336l128-80v-48L496 288zM496 784l-96 64v48l96-64zM496 688L368 768v48l128-80zM496 784l96 64v48l-96-64zM496 688l128 80v48L496 736z" fill="#ffffff" p-id="1669"></path><path d="M79.2 752.8h48l761.6-440 24-41.6h-48l-761.6 440z" fill="#ffffff" p-id="1670"></path><path d="M260 648l-7.2 115.2-41.6 24 7.2-115.2zM343.2 600l-5.6 151.2-41.6 24 5.6-151.2z" fill="#ffffff" p-id="1671"></path><path d="M260 648l-103.2-51.2-41.6 24L218.4 672zM343.2 600l-133.6-70.4-41.6 24L301.6 624zM731.2 376l103.2 51.2 41.6-24L772.8 352zM648 424l133.6 71.2 41.6-24L689.6 400z" fill="#ffffff" p-id="1672"></path><path d="M731.2 376l7.2-115.2 41.6-24-7.2 115.2zM648 424l5.6-150.4 41.6-24-5.6 150.4z" fill="#ffffff" p-id="1673"></path><path d="M912.8 752.8h-48l-761.6-440-24-41.6h48l761.6 440z" fill="#ffffff" p-id="1674"></path><path d="M732 648l7.2 115.2 41.6 24-7.2-115.2zM648.8 600l5.6 151.2 41.6 24-5.6-151.2z" fill="#ffffff" p-id="1675"></path><path d="M732 648l103.2-51.2 41.6 24L773.6 672zM648.8 600l133.6-70.4 41.6 24L690.4 624zM260.8 376l-103.2 51.2-41.6-24L219.2 352zM344 424l-133.6 71.2-41.6-24L302.4 400zM260.8 376l-7.2-115.2-41.6-24 7.2 115.2z" fill="#ffffff" p-id="1676"></path><path d="M344 424l-5.6-150.4-41.6-24 5.6 150.4z" fill="#ffffff" p-id="1677"></path><path d="M496 663.2l-131.2-75.2V436.8L496 360.8l131.2 75.2v151.2L496 663.2zM404.8 564.8L496 616.8l91.2-52.8v-104L496 407.2l-91.2 52.8v104.8z" fill="#ffffff" p-id="1678"></path></svg>';
        document.body.appendChild(this.node);
    };
    this.createSnow();
    this.snowMove = function () {
        this.node.style.top = parseInt(this.node.style.top) + this.yspeed + 'px';//垂直方向运动
        this.node.style.left = parseInt(this.node.style.left) + this.xsin * Math.sin(this.xspeed) + 'px';
        this.node.style.fontSize = this.size + 'px';
//            this.node.style.color='rgb('+Math.ceil(Math.random()*255)+','+Math.ceil(Math.random()*255)+','+Math.ceil(Math.random()*255)+')';
    };
}

//    创建雪花
function createSnow() {
    aSnow.push(new snow(Math.random() * oPageX, -50, 0.02 + Math.random() / 10, 1 + Math.random(), Math.random() * 30, 20 + Math.random() * 30));
}

setInterval(createSnow, 1000);//一秒钟创建一个雪花
//    雪花移动函数
function snowMove() {
    for (var j = 0; j < aSnow.length; j++) {
        aSnow[j].snowMove();
        if (parseInt(aSnow[j].node.style.top) > oPageY || parseInt(aSnow[j].node.style.left) > oPageX) {
            aSnow[j].node.parentNode.removeChild(aSnow[j].node);
            aSnow.splice(j, 1);
        }
    }
}

setInterval(snowMove, 10);