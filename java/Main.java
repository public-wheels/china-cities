import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Main {

    public static void main(String[] args) {
        List<String> data = loadData();
        List<Province> provinces = parseData(data);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        storeData(gson.toJson(provinces));
    }

    private static void storeData(String data) {
        Path path = Paths.get("china_cities.json");
        byte[] bytes = data.getBytes();
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))) {
            out.write(bytes, 0, bytes.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    private static List<Province> parseData(List<String> data) {
        Map<String, Province> provinceMap = new HashMap<>();
        Map<String, PrefectureCity> prefectureCityMap = new HashMap<>();
        for (String line : data) {
            String[] parts = line.split("\t");
            String nameZh = parts[2];
            String name = parts[1];
            String code = parts[0];
            String longitude = parts[11];
            String latitude = parts[10];
            String provinceNameZh = parts[7];
            String provinceName = parts[6];
            String prefectureNameZh = parts[9];
            String prefectureName = parts[8];
            provinceMap.putIfAbsent(provinceName, new Province(provinceNameZh, provinceName, new
                    ArrayList<>()));
            if (!prefectureCityMap.containsKey(prefectureNameZh)) {
                PrefectureCity prefectureCity = new PrefectureCity(prefectureNameZh, prefectureName, new ArrayList<>());
                provinceMap.get(provinceName).getPrefectureCities().add(prefectureCity);
                prefectureCityMap.put(prefectureNameZh, prefectureCity);
            }
            prefectureCityMap.get(prefectureNameZh).getCities().add(new City(nameZh, name, code, longitude, latitude));
        }
        return new ArrayList<>(provinceMap.values());
    }

    private static List<String> loadData() {
        Path file = Paths.get("china_cities.txt");
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CN")) {
                    data.add(line);
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return data;
    }
}
