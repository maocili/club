// JavaScript Document

    //鍒涘缓鍜屽垵濮嬪寲鍦板浘鍑芥暟锛�
    function initMap(){
        createMap();//鍒涘缓鍦板浘
        setMapEvent();//璁剧疆鍦板浘浜嬩欢
        addMapControl();//鍚戝湴鍥炬坊鍔犳帶浠�
        addMarker();//鍚戝湴鍥句腑娣诲姞marker
    }
    
    //鍒涘缓鍦板浘鍑芥暟锛�
    function createMap(){
        var map = new BMap.Map("dituContent");//鍦ㄧ櫨搴﹀湴鍥惧鍣ㄤ腑鍒涘缓涓€涓湴鍥�
        var point = new BMap.Point(116.460018,39.938637);//瀹氫箟涓€涓腑蹇冪偣鍧愭爣
        map.centerAndZoom(point,15);//璁惧畾鍦板浘鐨勪腑蹇冪偣鍜屽潗鏍囧苟灏嗗湴鍥炬樉绀哄湪鍦板浘瀹瑰櫒涓�
        window.map = map;//灏唌ap鍙橀噺瀛樺偍鍦ㄥ叏灞€
    }
    
    //鍦板浘浜嬩欢璁剧疆鍑芥暟锛�
    function setMapEvent(){
        map.enableDragging();//鍚敤鍦板浘鎷栨嫿浜嬩欢锛岄粯璁ゅ惎鐢�(鍙笉鍐�)
        map.enableScrollWheelZoom();//鍚敤鍦板浘婊氳疆鏀惧ぇ缂╁皬
        map.enableDoubleClickZoom();//鍚敤榧犳爣鍙屽嚮鏀惧ぇ锛岄粯璁ゅ惎鐢�(鍙笉鍐�)
        map.enableKeyboard();//鍚敤閿洏涓婁笅宸﹀彸閿Щ鍔ㄥ湴鍥�
    }
    
    //鍦板浘鎺т欢娣诲姞鍑芥暟锛�
    function addMapControl(){
        //鍚戝湴鍥句腑娣诲姞缂╂斁鎺т欢
    var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
    map.addControl(ctrl_nav);
                //鍚戝湴鍥句腑娣诲姞姣斾緥灏烘帶浠�
    var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
    map.addControl(ctrl_sca);
    }
    
    //鏍囨敞鐐规暟缁�
    var markerArr = [{title:"ESP&nbsp;BEIJING",content:"涓夐噷灞疭OHO&nbsp;6鍙峰晢鍦�1 灞�&nbsp;111鎴块棿",point:"116.460305|39.937254",isOpen:1,icon: {w:21,h:21,l:0,t:0,x:6,lb:5}}
         ];
    //鍒涘缓marker
    function addMarker(){
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            var p0 = json.point.split("|")[0];
            var p1 = json.point.split("|")[1];
            var point = new BMap.Point(p0,p1);
            var iconImg = createIcon(json.icon);
            var marker = new BMap.Marker(point,{icon:iconImg});
            var iw = createInfoWindow(i);
            var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
            marker.setLabel(label);
            map.addOverlay(marker);
            label.setStyle({
                        borderColor:"#808080",
                        color:"#333",
                        cursor:"pointer"
            });
            
            (function(){
                var index = i;
                var _iw = createInfoWindow(i);
                var _marker = marker;
                _marker.addEventListener("click",function(){
                    this.openInfoWindow(_iw);
                });
                _iw.addEventListener("open",function(){
                    _marker.getLabel().hide();
                })
                _iw.addEventListener("close",function(){
                    _marker.getLabel().show();
                })
                label.addEventListener("click",function(){
                    _marker.openInfoWindow(_iw);
                })
                if(!!json.isOpen){
                    label.hide();
                    _marker.openInfoWindow(_iw);
                }
            })()
        }
    }
    //鍒涘缓InfoWindow
    function createInfoWindow(i){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
    }
    //鍒涘缓涓€涓狪con
    function createIcon(json){
        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    }
    
    initMap();//鍒涘缓鍜屽垵濮嬪寲鍦板浘