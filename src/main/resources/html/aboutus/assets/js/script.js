(function($) {
/*銇撱亾銇嬨倝*/

 $(function (){
var $chkbxFilter_all = $('#all');

    //绲炪倞杈笺伨銇亜銉溿偪銉炽倰銈儶銉冦偗鏅傘伀銉併偋銉冦偗銉溿儍銈偣銈掋偑銉曘伀銇欍倠
    $chkbxFilter_all.click(function() {
      $(".sort").prop('checked',false);
      $chkbxFilter_all.prop('checked',true);
  });

    //銉併偋銉冦偗銉溿儍銈偣銇屻偗銉儍銈仌銈屻仧鏅傘伄鍕曚綔
    $("#products_search label input").click(function() {

        $(this).parent().toggleClass("selected");

        $.each($chkbxFilter_tags, function() {
            if($('#' + this).is(':checked')) {
               $("#products_search_result " + $chkbxFilter_blocks + ":not(." + this + ")").addClass('hidden-not-' + this);
               $chkbxFilter_all.prop('checked',false).parent().removeClass("selected");
           }
           else if($('#' + this).not(':checked')) {
               $("#products_search_result " + $chkbxFilter_blocks + ":not(." + this + ")").removeClass('hidden-not-' + this);
           }
       });

        //銉併偋銉冦偗銉溿儍銈偣銇�1銇ゃ倐閬告姙銇曘倢銇︺亜銇亜鍫村悎銇€佺禐銈婅炯銇俱仾銇勩儨銈裤兂銇玞lass="selected"銈掋仱銇戙倠
        if ($('.sort:checked').length == 0 ){
            $chkbxFilter_all.prop('checked',true).parent().addClass("selected");
            $(".sort").parent().removeClass("selected");
        };
    });
});

/*銇撱亾銇俱仹*/
})(jQuery);