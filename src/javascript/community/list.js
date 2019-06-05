import "@baseSrc/javascript/common/public";
import "@baseSrc/javascript/common/sidebar";
import Vue from "vue";
import {Message, MessageBox} from "element-ui";
import commonFunction from "@base/lib/javascript/common";

$(document).ready(() => {
    Vue.prototype.$message = Message;
    Vue.directive(MessageBox.name, MessageBox);
    Vue.prototype.$confirm = MessageBox.confirm;
    new Vue({
        el: "#community_list",
        data: {
            csrf: csrf,
            communities: communities
        },
        methods: {
            /**
             * 删除社区
             * @param id
             */
            deleteObject(id) {
                commonFunction.deleteObject(this, deleteUrl, id);
            }
        }
    })
    ;
})
;
