import java.util.List;

public class Province {
    private String ProvinceNameZh;
    private String ProvinceName;
    private List<PrefectureCity> prefectureCities;

    public Province(String ProvinceNameZh, String ProvinceName, List<PrefectureCity> prefectureCities) {
        this.ProvinceNameZh = ProvinceNameZh;
        this.ProvinceName = ProvinceName;
        this.prefectureCities = prefectureCities;
    }

    public List<PrefectureCity> getPrefectureCities() {
        return prefectureCities;
    }

    public void setPrefectureCities(List<PrefectureCity> prefectureCities) {
        this.prefectureCities = prefectureCities;
    }
}
