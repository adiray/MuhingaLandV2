package com.example.dell.muhingalandv2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.mikepenz.fastadapter.items.AbstractItem;

public class SongResponse {

    @SerializedName("created")
    private long created;

    @SerializedName("songs")
    private ArrayList<SongsItem> songs;

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

    public void setSongs(ArrayList<SongsItem> songs) {
        this.songs = songs;
    }

    public ArrayList<SongsItem> getSongs() {
        return songs;
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
                "SongResponse{" +
                        "created = '" + created + '\'' +
                        ",songs = '" + songs + '\'' +
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


  /*  @Override
    public SongResponse.SongViewHolder getViewHolder(View v) {
        return new SongViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.single_song_item_song_name;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.songs_view_single_song_layout;
    }


    @Override
    public void bindView(SongViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);


        holder.title_vh.setText(getSongs().get(holder.getAdapterPosition()).getArtist());
        Glide.with(holder.itemView).load(getSongs().get(holder.getAdapterPosition()).getCoverImage()).into(holder.song_cover_vh);


    }


    protected static class SongViewHolder extends RecyclerView.ViewHolder {

        //declaring the views
        TextView title_vh;
        ImageView song_cover_vh;
        ImageButton play_button, pause_button, stop_button, download_button;

        public SongViewHolder(View itemView) {
            super(itemView);

            //assigning the previously declared views
            title_vh = itemView.findViewById(R.id.single_song_item_song_name);
            song_cover_vh = itemView.findViewById(R.id.single_song_song_cover_image);
            pause_button = itemView.findViewById(R.id.single_song_item_pause_button);
            play_button = itemView.findViewById(R.id.single_song_item_play_button);
            stop_button = itemView.findViewById(R.id.single_song_item_stop_button);
            download_button = itemView.findViewById(R.id.single_song_item_download_button);

        }
    }

    extends AbstractItem<SongResponse, SongResponse.SongViewHolder>

*/

}