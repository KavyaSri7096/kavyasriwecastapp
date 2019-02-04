package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class Vod extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("runtime")
    private String runtime;

    @SerializedName("rating")
    private String rating;

    @SerializedName("parental_rating_id")
    private String parentalRatingId;

    @SerializedName("parental_rating")
    private ParentalRating parentalRating;

    @SerializedName("type")
    private int type;

    @SerializedName("type_name")
    private String typeName;

    @SerializedName("multi_event_vod_id")
    private int multiEventVodId = 0;

    @SerializedName("multi_event_vod_season_id")
    private int multiEventVodSeasonId = 0;

    @SerializedName("episode_number")
    private String episodeNumber;

    @SerializedName("subscribed")
    private boolean subscribed;

    @SerializedName("writers")
    private RealmList<VodMember> writers;

    @SerializedName("actors")
    private RealmList<VodMember> actors;

    @SerializedName("genres")
    private RealmList<VodGenre> genres;

    @SerializedName("show_types")
    private RealmList<ShowType> showTypes;

    @SerializedName("year")
    private String year;

    @SerializedName("trailer_source")
    private VodSource trailerSource;

    @SerializedName("movie_source")
    private VodSource movieSource;

    @SerializedName("cover")
    private VodImage cover;

    @SerializedName("gallery")
    private RealmList<VodImage> gallery;

    @SerializedName("banners")
    private RealmList<VodImage> banners;

    @SerializedName("continue_watching")
    private VodContinueWatching continueWatching;

    @SerializedName("rate")
    private VodRating rate;

    private boolean isRecommended = false;

    private boolean isTrending = false;

    private boolean isContinueWatching = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getParentalRatingId() {
        return parentalRatingId;
    }

    public void setParentalRatingId(String parentalRatingId) {
        this.parentalRatingId = parentalRatingId;
    }

    public ParentalRating getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(ParentalRating parentalRating) {
        this.parentalRating = parentalRating;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getMultiEventVodId() {
        return multiEventVodId;
    }

    public void setMultiEventVodId(int multiEventVodId) {
        this.multiEventVodId = multiEventVodId;
    }

    public int getMultiEventVodSeasonId() {
        return multiEventVodSeasonId;
    }

    public void setMultiEventVodSeasonId(int multiEventVodSeasonId) {
        this.multiEventVodSeasonId = multiEventVodSeasonId;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public RealmList<VodMember> getWriters() {
        return writers;
    }

    public void setWriters(RealmList<VodMember> writers) {
        this.writers = writers;
    }

    public RealmList<VodMember> getActors() {
        return actors;
    }

    public void setActors(RealmList<VodMember> actors) {
        this.actors = actors;
    }

    public RealmList<VodGenre> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<VodGenre> genres) {
        this.genres = genres;
    }

    public RealmList<ShowType> getShowTypes() {
        return showTypes;
    }

    public void setShowTypes(RealmList<ShowType> showTypes) {
        this.showTypes = showTypes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public VodSource getTrailerSource() {
        return trailerSource;
    }

    public void setTrailerSource(VodSource trailerSource) {
        this.trailerSource = trailerSource;
    }

    public VodSource getMovieSource() {
        return movieSource;
    }

    public void setMovieSource(VodSource movieSource) {
        this.movieSource = movieSource;
    }

    public VodImage getCover() {
        return cover;
    }

    public void setCover(VodImage cover) {
        this.cover = cover;
    }

    public RealmList<VodImage> getGallery() {
        return gallery;
    }

    public void setGallery(RealmList<VodImage> gallery) {
        this.gallery = gallery;
    }

    public RealmList<VodImage> getBanners() {
        return banners;
    }

    public void setBanners(RealmList<VodImage> banners) {
        this.banners = banners;
    }

    public VodContinueWatching getContinueWatching() {
        return continueWatching;
    }

    public void setContinueWatching(VodContinueWatching continueWatching) {
        this.continueWatching = continueWatching;
    }

    public VodRating getRate() {
        return rate;
    }

    public void setRate(VodRating rate) {
        this.rate = rate;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public boolean isTrending() {
        return isTrending;
    }

    public void setTrending(boolean trending) {
        isTrending = trending;
    }

    public boolean isContinueWatching() {
        return isContinueWatching;
    }

    public void setContinueWatching(boolean continueWatching) {
        isContinueWatching = continueWatching;
    }
}