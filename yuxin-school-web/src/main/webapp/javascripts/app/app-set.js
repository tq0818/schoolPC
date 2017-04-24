(function($){
    $(function(){
        
    //    select2调用
        function formatState (state) {
            if (!state.id) { return state.text; }
            var $state = $(
                '<span><img src="../../../images/bg-color/' + state.element.value.toLowerCase() + '.png" class="img-flag" /> ' + state.text + '</span>'
            );
            return $state;
        };

        $(".js-example-templating").select2({
            templateResult: formatState,
            templateSelection: formatState,
            minimumResultsForSearch: Infinity
        });
    })
})(jQuery)
