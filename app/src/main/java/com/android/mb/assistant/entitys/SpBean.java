package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class SpBean implements Serializable {

    /**
     * auditId : 55
     * applicationId : 80
     * approverId : 1093
     * approverName : test
     * isComplete : 2
     * isAgree : 0
     * flowNo : 1
     */
    private int auditId;//审批id
    private int applicationId;//申请id
    private String approverId;//审批人id
    private String approverName;//审批人姓名
    private int isComplete;//1:审批中 2:审批完成
    private String isAgree;//0:同意  1:不同意
    private int flowNo;//flow_no(审批流程号 1 部门主管  2物资分管领导  3物资保存领导 4物资联系人))

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApproverId() {
        return approverId == null ? "" : approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName == null ? "" : approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public String getIsAgree() {
        return isAgree == null ? "" : isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

    public int getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(int flowNo) {
        this.flowNo = flowNo;
    }
}
