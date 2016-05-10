package com.gank.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

public  class Story extends RealmObject implements Serializable {
    //id
    @SerializedName("id")
    private long mId;

    //标题
    @SerializedName("title")
    private String mTitle;

    //图像地址（官方 API 使用数组形式。目前暂未有使用多张图片的情形出现，曾见无 images 属性的情况，请在使用中注意 ）
    @SerializedName("images")
    private RealmList<StringObject> mImageUrls;

    //图像地址 只有topStories才有
    @SerializedName("image")
    private String mImageUrl;

    //类型,作用未知
    @SerializedName("type")
    private int mType;

    //供 Google Analytics 使用
    @SerializedName("ga_prefix")
    private String mGaPrefix;

    //消息是否包含多张图片（仅出现在包含多图的新闻中)
    @SerializedName("multipic")
    private boolean mMultiPic;

    private boolean isCollected ;

    public boolean isCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public String getmGaPrefix() {
        return mGaPrefix;
    }

    public void setmGaPrefix(String mGaPrefix) {
        this.mGaPrefix = mGaPrefix;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public RealmList<StringObject> getmImageUrls() {
        return mImageUrls;
    }

    public void setmImageUrls(RealmList<StringObject> mImageUrls) {
        this.mImageUrls = mImageUrls;
    }

    public boolean ismMultiPic() {
        return mMultiPic;
    }

    public void setmMultiPic(boolean mMultiPic) {
        this.mMultiPic = mMultiPic;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

  /* @Override
    public String toString() {
        return "Story{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mImageUrls=" + mImageUrls +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mType=" + mType +
                ", mGaPrefix='" + mGaPrefix + '\'' +
                ", mMultiPic=" + mMultiPic +
                '}';
    }*/
}