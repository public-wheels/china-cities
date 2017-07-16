# china-cities

This repository provides basic information about Chinese cities.

## Format

There are two file formats for you.

### TXT Version

First you can get a txt version in china_cities.txt file, it is provided by [heweather](https://www.heweather.com/).

```
File Structure

-   code
-   name(English)
-   name(Chinese)
-   country code
-   country name(English)
-   country name(Chinese)
-   province name(English)
-   province name(Chinese)
-   prefecture-level city name(English)
-   prefecture-level city name(Chinese)
-   latitude
-   longitude
```

### JSON Version

Json file is transformed from txt version. You can check source code in java folder.

```
File Structure

-   province name(Chinese)
-   province name(English)
    -   prefecture city name(Chinese)
    -   prefecture city name(English)
        -   city name(Chinese)
        -   city name(Engilsh)
        -   code
        -   longitude
        -   latitude
......
```

## License

[MIT LICENSE](./LICENSE)