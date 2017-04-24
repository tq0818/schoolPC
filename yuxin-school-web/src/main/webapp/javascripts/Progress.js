
function Progress(obj){
    this.ele=document.getElementById(obj) || document.getElementsByClassName(obj)[0];
    this.Width=parseFloat(getComputedStyle(this.ele,false).width);
    this.fragment=document.createElement('div');
    this.fragment.className='proGres';
    this.scale=0;
    this.init();
}
Progress.prototype.init=function () {
    this.ele.style.position='relative';
    this.fragment.innerHTML='<div class="proBack"><span class="proValue"><small></small>%</span></div>';
    this.ele.appendChild( this.fragment);
};
Progress.prototype.degree=function (value) {
    this.scale=parseFloat(value)/100;
    this.ele.getElementsByTagName('small')[0].innerHTML=value;
    this.ele.getElementsByClassName('proBack')[0].style.width=this.scale*this.Width+'px';
};


