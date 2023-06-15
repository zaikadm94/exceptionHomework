package ru.netology.exception;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {

            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Продукт с  ID: " + product.getId() + " уже существует");
        }
        products = addToArray(products, product);
    }


    public Product[] findAll() {
        return products;
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void remove(int id) {
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }


    public void removeById(int id) { // переименовал метод в removeById
        Product productToRemove = findById(id);
        if (productToRemove == null) { // проверяем, есть ли искомый товар с данным Id
            throw new NotFoundException("Element with id: " + id + " not found"); // ....если товар не найден, "кидаем" исключение с сообщением предупреждения
        }
        Product[] tmp = new Product[products.length - 1]; //...если товар с данным ID найден, удаляем товар
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) { //добавил метод findById, предназначенный для поиска товара в репозитории по его ID.
        for (Product product :
                products) {   //....пробегается по всем товарам репозитория и сверяет их ID с искомым
            if (product.getId() == id) { //...в случае совпадения
                return product; //...делает return этого товара
            }
        }
        return null; // если товар не найден, делает return null
    }
}