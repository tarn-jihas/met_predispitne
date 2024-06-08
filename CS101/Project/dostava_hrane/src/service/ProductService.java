package service;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import model.Product;
import repository.IProductRepository;

import java.util.List;

public class ProductService implements IProductService<Product> {
    IProductRepository<Product> productRepository;

    public ProductService(IProductRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> showAllProducts() throws FileReadException {

        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) throws FileWriteException {
        productRepository.save(product);

    }

    @Override
    public void clearProducts() throws FileWriteException {
        productRepository.deleteAll();
    }


}
