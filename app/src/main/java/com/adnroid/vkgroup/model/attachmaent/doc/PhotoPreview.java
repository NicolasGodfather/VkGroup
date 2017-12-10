package com.adnroid.vkgroup.model.attachmaent.doc;

import io.realm.RealmList;
import io.realm.RealmObject;

public class PhotoPreview extends RealmObject {

    RealmList<Size> sizes;

    public RealmList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<Size> sizes) {
        this.sizes = sizes;
    }

}
