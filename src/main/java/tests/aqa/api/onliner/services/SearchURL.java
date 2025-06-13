package tests.aqa.api.onliner.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SearchURL {
     CATALOG_URL ("https://catalog.onliner.by/"),
     AUTO_URL ("https://go.onliner.by/tiles.acp/redirect/eyJ1cmwiOiJodHRwczpcL1wvYXV0by5vbmxpbmVyLmJ5XC8yMDI1XC8wM1wvMDZcL2RvbGctemEtYXZ0b3hsYW0iLCJpbmRleCI6MiwiaWRlbnRpdHkiOiIxOjA6MDoxNzQxMjM4MTcwIn0%3D"),
     BARACHOLKA_FIND_DOM_URL ("https://baraholka.onliner.by/search.php"),
    ABOUT_COMPANY_URL ("https://blog.onliner.by/about"),
    ;
    private String urlSearch;
}
