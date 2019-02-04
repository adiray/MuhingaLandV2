package com.example.dell.muhingalandv2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.mikepenz.fastadapter.items.AbstractItem;

public class ArtistViewAlbumResponse extends AbstractItem<ArtistViewAlbumResponse, ArtistViewAlbumResponse.AlbumResponseViewHolder> {

    @SerializedName("created")
    private long created;

    @SerializedName("album")
    private Album album;

    @SerializedName("name")
    private String name;

    @SerializedName("___class")
    private String ___class;

    @SerializedName("profile_picture")
    private String profilePicture;

    @SerializedName("cover_image")
    private String coverImage;

    @SerializedName("ownerId")
    private String ownerId;

    @SerializedName("updated")
    private long updated;

    @SerializedName("objectId")
    private String objectId;

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCreated() {
        return created;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setClass(String ___class) {
        this.___class = ___class;
    }

    public String get___class() {
        return ___class;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getUpdated() {
        return updated;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return
                "ArtistViewAlbumResponse{" +
                        "created = '" + created + '\'' +
                        ",album = '" + album + '\'' +
                        ",name = '" + name + '\'' +
                        ",___class = '" + ___class + '\'' +
                        ",profile_picture = '" + profilePicture + '\'' +
                        ",cover_image = '" + coverImage + '\'' +
                        ",ownerId = '" + ownerId + '\'' +
                        ",updated = '" + updated + '\'' +
                        ",objectId = '" + objectId + '\'' +
                        "}";
    }

    @Override
    public AlbumResponseViewHolder getViewHolder(View v) {
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }


    /*******************************************************************************************************************************************************
     * FAST ADAPTER CODE STARTS HERE
     *
     */



    protected static class AlbumResponseViewHolder extends RecyclerView.ViewHolder {

        //declaring the views
        ImageView artist_image_vh;
        TextView artist_name_vh;

        public AlbumResponseViewHolder(View itemView) {
            super(itemView);

            //assigning the previously declared views
            artist_image_vh = itemView.findViewById(R.id.musichome_single_artist_image);
            artist_name_vh = itemView.findViewById(R.id.activty_home_single_artist_name);
        }
    }



}