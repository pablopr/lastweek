$(document).ready(function(){
        $(".slider a").click(function(){
                var $this = $(this);
                if( $this.is('.hide-button') ) {
                        $this.next().slideUp("slow");
                        $this.removeClass('hide-button');
                        $this.addClass('show-button');
                }
                else {
                        $this.next().slideDown("slow");
                        $this.removeClass('show-button');
                        $this.addClass('hide-button');
                }
                return false;
        });
});