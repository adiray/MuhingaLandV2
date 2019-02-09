package com.example.dell.muhingalandv2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class Album extends AbstractItem<Album, Album.AlbumResponseViewHolder> {

    @SerializedName("created")
    private long created;

    @SerializedName("name")
    private String name;

    @SerializedName("___class")
    private String ___class;

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

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Object getOwnerId() {
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
                "Album{" +
                        "created = '" + created + '\'' +
                        ",name = '" + name + '\'' +
                        ",___class = '" + ___class + '\'' +
                        ",cover_image = '" + coverImage + '\'' +
                        ",ownerId = '" + ownerId + '\'' +
                        ",updated = '" + updated + '\'' +
                        ",objectId = '" + objectId + '\'' +
                        "}";
    }


    /*******************************************************************************************************************************************************
     * FAST ADAPTER CODE STARTS HERE
     *
     */


    @Override
    public AlbumResponseViewHolder getViewHolder(View v) {
        return new AlbumResponseViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.artist_view_album_image;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.artist_view_single_album_view;
    }


    @Override
    public void bindView(AlbumResponseViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);


        holder.album_name_vh.setText(getName());
        Glide.with(holder.itemView).load(getCoverImage()).into(holder.album_image_vh);


    }

    protected static class AlbumResponseViewHolder extends RecyclerView.ViewHolder {

        //declaring the views
        ImageView album_image_vh;
        TextView album_name_vh;

        public AlbumResponseViewHolder(View itemView) {
            super(itemView);

            //assigning the previously declared views
            album_image_vh = itemView.findViewById(R.id.artist_view_album_image);
            album_name_vh = itemView.findViewById(R.id.artist_view_album_name);
        }
    }


}