(function(){
	if(CKEDITOR.plugins.get("video")){
		return;
	}
	CKEDITOR.plugins.add("video", {
        requires: "dialog,fakeobjects",
        onLoad: function() {
            CKEDITOR.addCss("img.cke_flash{background-image: url(" + CKEDITOR.getUrl(this.path + "images/placeholder.png") + ");background-position: center center;background-repeat: no-repeat;border: 1px solid #a9a9a9;width: 80px;height: 80px;}")
        },
        init: function(a) {
            var b = "object[classid,codebase,height,hspace,vspace,width];param[name,value];embed[height,hspace,pluginspage,src,type,vspace,width]";
            CKEDITOR.dialog.isTabEnabled(a, "video", "properties") && (b += ";object[align]; embed[allowscriptaccess,quality,scale,wmode]");
            CKEDITOR.dialog.isTabEnabled(a, "video", "advanced") && (b += ";object[id]{*}; embed[bgcolor]{*}(*)");
            a.addCommand("video", new CKEDITOR.dialogCommand("video",{
                allowedContent: b,
                requiredContent: "embed"
            }));
            a.ui.addButton && a.ui.addButton("Video", 
            {
                label: "视频",
                command: "video",
                toolbar: "insert,20"
            });
            CKEDITOR.dialog.add("video", this.path + "dialogs/video.js");
            a.addMenuItems && a.addMenuItems({
                flash: {
                    label: a.lang.flash.properties,
                    command: "flash",
                    group: "flash"
                }
            });
            a.on("doubleclick", function(a) {
                var b = a.data.element;
                b.is("img") && "flash" == b.data("cke-real-element-type") && (a.data.dialog = "video")
            }
            );
            a.contextMenu && a.contextMenu.addListener(function(a) {
                if (a && a.is("img") && !a.isReadOnly() && "flash" == a.data("cke-real-element-type"))
                    return {
                        flash: CKEDITOR.TRISTATE_OFF
                    }
            }
            )
        },
        afterInit: function(a) {
            var b = a.dataProcessor;
            (b = b && b.dataFilter) && b.addRules({
                elements: {
                    "cke:object": function(b) {
                        var c = b.attributes;
                        if ((!c.classid || !("" + c.classid).toLowerCase()) && !d(b)) {
                            for (c = 0; c < b.children.length; c++)
                                if ("cke:embed" == b.children[c].name) {
                                    if (!d(b.children[c]))
                                        break;
                                    return e(a, b)
                                }
                            return null 
                        }
                        return e(a, b)
                    },
                    "cke:embed": function(b) {
                        return !d(b) ? null  : e(a, b)
                    }
                }
            }, 5)
        }
    })
})()