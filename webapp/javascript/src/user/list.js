import "@base/javascript/src/common/public";
import "@base/javascript/src/common/sidebar";
import Vue from "vue";
import {Message, MessageBox} from "element-ui";
import commonFunction from "@base/lib/javascript/common";

$(document).ready(() => {
    Vue.prototype.$message = Message;
    Vue.directive(MessageBox.name, MessageBox);
    Vue.prototype.$confirm = MessageBox.confirm;
    new Vue({
        el: "#user_list",
        data: {
            token: token,
            systemAdministratorId: systemAdministratorId,
            systemUsers: systemUsers
        },
        methods: {
            /**
             * 系统用户锁定与开锁
             * @param systemUserId
             * @param isLocked
             * @param index
             */
            userLock(systemUserId, isLocked, index) {
                if (systemUserId === this.systemAdministratorId) {
                    this.$message({
                        message: "不允许锁定超级管理员！",
                        type: "error"
                    });
                    return;
                }
                $.ajax({
                    url: "/system/user_role/user/ajax_user_lock",
                    method: "get",
                    data: {
                        _token: this.token,
                        systemUserId: systemUserId,
                        locked: isLocked
                    }
                }).then((data) => {
                    let message = null;
                    let messageType = "success";
                    if (data.state === 1) {
                        let oldSystemUser = this.systemUsers[index];
                        oldSystemUser.isLocked = !oldSystemUser.isLocked;
                        this.$set(this.systemUsers, index, oldSystemUser);
                        message = data.isLocked === 0 ? "解锁成功，该用户下次将能够登录系统！" : "锁定成功，该用户下次将无法登录系统！";
                    } else {
                        messageType = "error";
                        message = isLocked ? "解锁失败，该用户下次将无法登录系统！" : "锁定失败，该用户下次将能够登录系统！";
                    }
                    this.$message({
                        message: message,
                        type: messageType
                    });
                });
            },
            /**
             * 删除系统用户
             * @param id
             */
            deleteObject(id) {
                if (id === this.systemAdministratorId) {
                    this.$message({
                        message: "不允许删除超级管理员！",
                        type: "error"
                    });
                    return;
                }
                commonFunction.deleteObject(this, deleteUrl, id);
            }
        }
    })
    ;
})
;
