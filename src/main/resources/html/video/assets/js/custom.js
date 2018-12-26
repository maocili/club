(function($) {
/*銇撱亾銇嬨倝*/

//=====================================================
//銈兂銈兗銉氥兗銈稿唴銉兂銈�
$(function() {
  $(".scroll").click(function(event){
    event.preventDefault();
    var url = this.href;
    var parts = url.split("#");
    var target = parts[1];
    var target_offset = $("#"+target).offset();
    var target_top = target_offset.top;
    $('html, body').animate({scrollTop:target_top}, "slow");
  });
});

//=====================================================
//銉堛儍銉椼儦銉笺偢 box
$(function(){
    $(".info_box").heightLine();
    $(".video_items").heightLine();
  });

//=====================================================
//銉堛儍銉椼儦銉笺偢 main_slider
  $( document ).ready(function( $ ) {
    $('#main_slider').sliderPro({
      width: 1500,
arrows: true,//宸﹀彸銇煝鍗�
buttons: true,//銉娿儞銈层兗銈枫儳銉炽儨銈裤兂
slideDistance:1,//銈广儵銈ゃ儔鍚屽＋銇窛闆�
autoHeight:true,
fadeOutPreviousSlide: true,
visibleSize: true,
centerImage: true,
autoplayDelay: 3000
});
  });

/*銇撱亾銇俱仹*/
})(jQuery);