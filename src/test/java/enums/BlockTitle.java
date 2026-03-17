package enums;

public enum BlockTitle {
    BLOG("Будьте в курсе последних новостей (блог)"),
    FAST_NAVIGATION("AB: Каталог быстрой навигации"),
    VIDEO_GALLERY("AB: Видео товаров");

    private final String value;

    BlockTitle(String value) {
        this.value = value;
        }

    public String value() {
        return value;
    }
}