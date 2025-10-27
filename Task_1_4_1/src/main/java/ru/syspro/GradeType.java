package ru.syspro;

enum GradeType {
    ОТЛИЧНО,
    ХОРОШО,
    УДОВЛЕТВОРИТЕЛЬНО,
    НЕУДОВЛЕТВОРИТЕЛЬНО,
    КВАЛИФИКАЦИОННАЯ_РАБОТА_ОТЛИЧНО;

    public int getNumericValue() {
        switch (this) {
            case ОТЛИЧНО:
            case КВАЛИФИКАЦИОННАЯ_РАБОТА_ОТЛИЧНО:
                return 5;
            case ХОРОШО:
                return 4;
            case УДОВЛЕТВОРИТЕЛЬНО:
                return 3;
            default:
                return 2;
        }
    }
}