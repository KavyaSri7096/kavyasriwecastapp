package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class TVShow extends RealmObject {

    @PrimaryKey
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("episode_runtime")
    private String episodeRuntime;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("lastAirdate")
    private String lastAirdate;

    @SerializedName("rating")
    private String rating;

    @SerializedName("year")
    private String year;

    @SerializedName("season_count")
    private int seasonCount;

    @SerializedName("episode_count")
    private int episodeCount;

    @SerializedName("seasons")
    private RealmList<TVShowSeason> seasons;

    @SerializedName("parental_rating_id")
    private int parentalRatingId;

    @SerializedName("parental_rating")
    private ParentalRating parentalRating;

    @SerializedName("writers")
    private RealmList<VodMember> writers;

    @SerializedName("actors")
    private RealmList<VodMember> actors;

    @SerializedName("directors")
    private RealmList<String> directors;

    @SerializedName("genres")
    private RealmList<TVShowGenre> genres;

    @SerializedName("show_types")
    private RealmList<ShowType> showTypes;

    @SerializedName("covers")
    private RealmList<VodImage> covers;

    @SerializedName("gallery")
    private RealmList<VodImage> gallery;

    @SerializedName("banners")
    private RealmList<VodImage> banners;

    @SerializedName("rate")
    private VodRating rate;

    @SerializedName("trailer_source")
    private VodSource trailerSource;

    private boolean isRecommended = false;

    private boolean isTrending = false;

    private Date created;

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

    public String getEpisodeRuntime() {
        return episodeRuntime;
    }

    public void setEpisodeRuntime(String episodeRuntime) {
        this.episodeRuntime = episodeRuntime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLastAirdate() {
        return lastAirdate;
    }

    public void setLastAirdate(String lastAirdate) {
        this.lastAirdate = lastAirdate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public RealmList<TVShowSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(RealmList<TVShowSeason> seasons) {
        this.seasons = seasons;
    }

    public int getParentalRatingId() {
        return parentalRatingId;
    }

    public void setParentalRatingId(int parentalRatingId) {
        this.parentalRatingId = parentalRatingId;
    }

    public ParentalRating getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(ParentalRating parentalRating) {
        this.parentalRating = parentalRating;
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

    public RealmList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(RealmList<String> directors) {
        this.directors = directors;
    }

    public RealmList<TVShowGenre> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<TVShowGenre> genres) {
        this.genres = genres;
    }

    public RealmList<ShowType> getShowTypes() {
        return showTypes;
    }

    public void setShowTypes(RealmList<ShowType> showTypes) {
        this.showTypes = showTypes;
    }

    public RealmList<VodImage> getCovers() {
        return covers;
    }

    public void setCovers(RealmList<VodImage> covers) {
        this.covers = covers;
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

    public VodRating getRate() {
        return rate;
    }

    public void setRate(VodRating rate) {
        this.rate = rate;
    }

    public VodSource getTrailerSource() {
        return trailerSource;
    }

    public void setTrailerSource(VodSource trailerSource) {
        this.trailerSource = trailerSource;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
