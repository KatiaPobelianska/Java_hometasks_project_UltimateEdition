package spring.study.model;
/* Разработайте приложение для отображения и обработки простой формы обратной связи
 о товаре (оценка от 0 до 5, сообщение с отзывом). Приложение должно предоставлять
 представление формы обратной связи, а также сохранять отправленные пользователем данные.
  У товара есть идентификатор и название. Создайте метод, который по id товара будет возвращать
   представление вида «Средняя оценка товара Расчёска:
 4.35 из 5 на основании 132 отзывов».*/
public class Review {
    private int id;
    private String name;
    private String description;
    private int mark;
    private int productId;

    public Review(int id, String name, String description, int mark, int productId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mark = mark;
        this.productId = productId;

    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
