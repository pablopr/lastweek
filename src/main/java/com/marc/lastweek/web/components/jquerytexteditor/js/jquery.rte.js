
$(document).ready(function() {

    $.fn.html = function(iframe) {
        return iframe.contentWindow.document.getElementsByTagName("body")[0].innerHTML;
    };

    $.fn.defaults = {
        dot_net_button_class: null,
        max_height: 350
    };

    var opts = $.extend($.fn.defaults);

    var $this = $(".rte-zone");
    return $this.each( function() {

        var textarea = $(this);
        var iframe;
        var element_id = textarea.attr("id");

        function enableDesignMode() {

            var content = textarea.val();

            // Mozilla needs this to display caret
            if($.trim(content)=='') {
                content = '<br />';
            }

            // for compatibility reasons, need to be created this way
            iframe = document.createElement("iframe");
            iframe.frameBorder=0;
            iframe.frameMargin=0;
            iframe.framePadding=0;
            iframe.height=200;
            if(textarea.attr('class'))
                iframe.className = textarea.attr('class');
            if(textarea.attr('id'))
                iframe.id = element_id;
            if(textarea.attr('name'))
                iframe.title = textarea.attr('name');

            textarea.after(iframe);

            var doc = "<html><head></head><body class='frameBody'>"+content+"</body></html>";
            tryEnableDesignMode(doc, function() {
                $(".toolbar-" + element_id).remove();
                textarea.before(toolbar());
                // hide textarea
                textarea.hide();

            });

        }

        function tryEnableDesignMode(doc, callback) {
            if(!iframe) { return false; }

            try {
                iframe.contentWindow.document.open();
                iframe.contentWindow.document.write(doc);
                iframe.contentWindow.document.close();
            } catch(error) {
                //console.log(error);
            }
            if (document.contentEditable) {
                iframe.contentWindow.document.designMode = "On";
                callback();
                return true;
            }
            else if (document.designMode != null) {
                try {
                    iframe.contentWindow.document.designMode = "on";
                    callback();
                    return true;
                } catch (error) {
                    //console.log(error);
                }
            }
            setTimeout(function(){tryEnableDesignMode(doc, callback)}, 500);
            return false;
        }

        function disableDesignMode(submit) {
            var content = iframe.contentWindow.document.getElementsByTagName("body")[0].innerHTML;

            if($(iframe).is(":visible")) {
                textarea.val(content);
            }

            if(submit != true) {
                textarea.show();
                $(iframe).hide();
            }
        }

        // create toolbar and bind events to it's elements
        function toolbar() {
            var tb = $("<div class='rte-toolbar' class='toolbar-"+ element_id +"'><div>\
                <p>\
                    <a href='#' class='bold'><img src='${BOLD_IMG}' alt='bold' /></a>\
                    <a href='#' class='italic'><img src='${ITALIC_IMG}' alt='italic' /></a>\
                </p>\
                <p>\
                    <a href='#' class='unorderedlist'><img src='${UNORDERED_IMG}' alt='unordered list' /></a>\
                    <a href='#' class='link'><img src='${LINK_IMG}' alt='link' /></a>\
                </p></div></div>");


            $('.bold', tb).click(function(){ formatText('bold');return false; });
            $('.italic', tb).click(function(){ formatText('italic');return false; });
            $('.unorderedlist', tb).click(function(){ formatText('insertunorderedlist');return false; });
            $('.link', tb).click(function(){
                var p=prompt("URL:");
                if(p)
                    formatText('CreateLink', p);
                return false; });

            $(iframe).parents('form').submit(function(){
                    disableDesignMode(true);
                });

            var iframeDoc = $(iframe.contentWindow.document);

            iframeDoc.keyup(function() {
                var body = $('body', iframeDoc);
                if(body.scrollTop() > 0) {
                    var iframe_height = parseInt(iframe.style['height'])
                    if(isNaN(iframe_height))
                        iframe_height = 0;
                    var h = Math.min(opts.max_height, iframe_height+body.scrollTop()) + 'px';
                    iframe.style['height'] = h;
                }
                return true;
            });

            return tb;
        };

        function formatText(command, option) {
            iframe.contentWindow.focus();
            try{
                iframe.contentWindow.document.execCommand(command, false, option);
            }catch(e){
                //console.log(e)
            }
            iframe.contentWindow.focus();
        };
        enableDesignMode();

    }); 
});

