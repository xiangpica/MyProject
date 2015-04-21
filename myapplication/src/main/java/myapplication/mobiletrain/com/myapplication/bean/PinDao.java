package myapplication.mobiletrain.com.myapplication.bean;

/**
 * Created by 悄悄是别离的笙箫 on 2015/4/21.
 */
public class PinDao {

    private String nowepisodes;
    private String areaname;
    private String vt;
    private String releasedate;
    private String aid;
    private String starringname;
    private String episodes;
    private String isend;
    private String subcategoryname;
    private String directory;
    private String name;
    private String poster;
    private String rating;

    public PinDao() {
    }

    public PinDao(String nowepisodes, String areaname, String vt, String releasedate, String aid, String starringname, String episodes, String isend, String subcategoryname, String directory, String name, String poster, String rating) {
        this.nowepisodes = nowepisodes;
        this.areaname = areaname;
        this.vt = vt;
        this.releasedate = releasedate;
        this.aid = aid;
        this.starringname = starringname;
        this.episodes = episodes;
        this.isend = isend;
        this.subcategoryname = subcategoryname;
        this.directory = directory;
        this.name = name;
        this.poster = poster;
        this.rating = rating;
    }

    public String getNowepisodes() {
        return nowepisodes;
    }

    public void setNowepisodes(String nowepisodes) {
        this.nowepisodes = nowepisodes;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getVt() {
        return vt;
    }

    public void setVt(String vt) {
        this.vt = vt;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getStarringname() {
        return starringname;
    }

    public void setStarringname(String starringname) {
        this.starringname = starringname;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getIsend() {
        return isend;
    }

    public void setIsend(String isend) {
        this.isend = isend;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
