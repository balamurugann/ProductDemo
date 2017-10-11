package productdemo.bb.com.productdemo.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.bb.productdemo.database.AppDatabase;
import com.bb.productdemo.database.dao.ProductDao;
import com.bb.productdemo.database.domain.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author  bala.natarajan
 */
@RunWith(AndroidJUnit4.class)
public class ProductTest {
    private ProductDao productDao;
    private AppDatabase db;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        productDao = db.getProductDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void shouldCreateDatabase() {
        assertNotNull(db);
    }

    @Test
    public void shouldCreateDao() {
        assertNotNull(productDao);
    }

    @Test
    public void shouldInsertNote() {
        Product product = new Product();
        product.setName("samsung");
        product.setDescription("product description");
        productDao.insert(product);

        List<Product> lstProduct = productDao.getAllProductForTest();

        assertEquals(1, lstProduct.size());
        Product dbProduct = lstProduct.get(0);
        assertEquals(product.getName(), dbProduct.getName());
        assertEquals(product.getDescription(), dbProduct.getDescription());
        assertEquals(1, dbProduct.getId());
    }

    @Test
    public void shouldDeleteNote() {
        Product product = new Product();
        product.setName("Mac");
        productDao.insert(product);
        List<Product> products = productDao.getAllProductForTest();

        assertEquals(1, products.size());
        productDao.delete(products.get(0));
        products = productDao.getAllProductForTest();
        assertEquals(0, products.size());
    }

}