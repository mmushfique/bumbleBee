package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.BrandManager;
import com.mush.bumblebee.domain.Brand;
import com.mush.bumblebee.injector.Injector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BrandServiceImpl implements BrandService{
    private static BrandServiceImpl service;
    private final Injector injector = new Injector();
    private final BrandManager brandManager=injector.getBrandManager();

    private BrandServiceImpl() {
    }

    public static synchronized BrandServiceImpl getBrandServiceInstance() {
        if (service == null) {
            service = new BrandServiceImpl();
        }
        return service;
    }

    @Override
    public String registerBrand(Brand brand) throws SQLException, ClassNotFoundException, IOException {
        return brandManager.registerBrand(brand);
    }

    @Override
    public Brand getSpecificBrand(String brandName) throws SQLException, ClassNotFoundException, IOException {
        return brandManager.getSpecificBrand(brandName);
    }

    @Override
    public List<Brand> getAllBrands() throws SQLException, ClassNotFoundException, IOException {
        return brandManager.getAllBrands();
    }

    @Override
    public String updateBrand(Brand brand) throws SQLException, IOException, ClassNotFoundException {
        return brandManager.updateBrand(brand);
    }

    @Override
    public boolean deleteBrand(String brandName) throws SQLException, ClassNotFoundException, IOException {
        return brandManager.deleteBrand(brandName);
    }
}
