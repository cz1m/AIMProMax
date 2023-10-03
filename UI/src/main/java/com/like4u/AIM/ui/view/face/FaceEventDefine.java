package com.like4u.AIM.ui.view.face;

/**
 * 标签框事件
 */

public class FaceEventDefine {

    private FaceInit faceInit;

    public FaceEventDefine(FaceInit faceInit) {
        this.faceInit = faceInit;

        hideFace();
    }

    /**
     * 隐藏表情框
     */

    private void hideFace(){
        faceInit.root().setOnMouseExited(event -> {
            faceInit.hide();
        });
    }

}
