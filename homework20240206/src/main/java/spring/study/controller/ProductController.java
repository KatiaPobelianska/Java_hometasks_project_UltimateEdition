package spring.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.study.dao.ProductDao;
import spring.study.dao.ReviewDao;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductDao productDao;
    private final ReviewDao reviewDao;

    @Autowired
    public ProductController(ProductDao productDao, ReviewDao reviewDao) {
        this.productDao = productDao;
        this.reviewDao = reviewDao;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("products", productDao.getAll());
        return "allProducts";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable("id") int productId) {
        model.addAttribute("product", productDao.getProductById(productId));
        model.addAttribute("reviews", reviewDao.getListOfReviewsById(productId));
        model.addAttribute("averageRating", productDao.getAverageRatingForProduct(productId));
        return "product";
    }



}
