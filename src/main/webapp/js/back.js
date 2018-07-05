function plusReady(){
// 隐藏滚动条
	plus.webview.currentWebview().setStyle({scrollIndicator:'none'});
// Android处理返回键
	plus.key.addEventListener('backbutton',function(){
		//plus.webview.goBack();
		window.history.go(-1);
	},false);
}
if(window.plus){
	plusReady();
}else{
	document.addEventListener('plusready',plusReady,false);
}
//DOMContentLoaded事件处理
var _domReady=false;
document.addEventListener('DOMContentLoaded',function(){
	_domReady=true;
},false);