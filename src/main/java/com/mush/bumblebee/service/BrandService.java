package com.mush.bumblebee.service;

import com.mush.bumblebee.domain.Brand;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BrandService {
    boolean registerBrand(Brand brand) throws SQLException, ClassNotFoundException, IOException;

    Brand getSpecificBrand(String brandName) throws SQLException, ClassNotFoundException, IOException;

    List<Brand> getAllBrands() throws SQLException, ClassNotFoundException, IOException;

    boolean updateBrand(Brand brand) throws SQLException, IOException, ClassNotFoundException;

    boolean deleteBrand(String brandName) throws SQLException, ClassNotFoundException, IOException;
}
