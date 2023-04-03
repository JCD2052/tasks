import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import org.jcd2052.models.ProductDimension;
import org.jcd2052.pages.MainPage;
import org.jcd2052.pages.forms.FacetCategoriesForm;
import org.jcd2052.pages.forms.FacetCategoryTab;
import org.jcd2052.pages.forms.ProductInfoForm;
import org.jcd2052.pages.forms.SceneForm;
import org.jcd2052.pages.forms.SideBarTab;
import org.jcd2052.utils.ImageUtils;
import org.openqa.selenium.OutputType;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeoutException;

public class RoomStylerTest extends BaseTest {
    private final MainPage mainPage = new MainPage();
    private final FacetCategoriesForm facetCategoriesForm = new FacetCategoriesForm();
    private final ProductInfoForm productInfoForm = new ProductInfoForm();
    private final SceneForm sceneForm = new SceneForm();

    @Test(invocationCount = 10)
    public void dragAndDropObjectThenDelete() throws TimeoutException, FindFailed {
        Assert.assertTrue(mainPage.waitForLoading(), "Main page hasn't been loaded");

        mainPage.getGreetForm().closePopupButton();
        Assert.assertTrue(mainPage.getGreetForm().isFormClosed(),
                "Form hasn't been closed");

        BufferedImage workplaceImageWithNoProducts = mainPage.getImageOfWorkplace();
        Location centreOfTheScreen = new Location(workplaceImageWithNoProducts.getWidth() / 2,
                workplaceImageWithNoProducts.getHeight() / 2);

        mainPage.getSideBarForm().selectTab(SideBarTab.FURNISH);
        facetCategoriesForm.selectCategory(FacetCategoryTab.DINNING_ROOM);
        Assert.assertTrue(facetCategoriesForm.waitUntilListLoaded(),
                "Product list hasn't been loaded");

        IButton randomProduct = facetCategoriesForm.getRandomProduct();
        BufferedImage elementImage = ImageUtils.readImage(randomProduct.getElement()
                .getScreenshotAs(OutputType.FILE));

        Pattern pattern = new Pattern(elementImage);
        screen.dragDrop(pattern, centreOfTheScreen);

        mainPage.getSideBarForm().hideSideBar();
        AqualityServices.getConditionalWait()
                .waitForTrue(() -> mainPage.getSideBarForm().isSidebarHidden());

        BufferedImage workplaceImageWithProduct = mainPage.getImageOfWorkplace();
        BufferedImage productImage = RoomStylerTestSteps.getProductImage(workplaceImageWithProduct);

        Pattern elementPattern = new Pattern(productImage);
        Assert.assertNotNull(screen.exists(elementPattern),
                "Product has been not found.");
        screen.click(elementPattern);

        ProductDimension productDimensions = productInfoForm.getProductDimensions();
        Assert.assertTrue(productDimensions.valuesAreNotNull(), "Some values are 0. "
                + productDimensions);

        screen.type(Key.DELETE);

        int elementsCountInScene = sceneForm.getElementsCountInScene();
        Assert.assertEquals(elementsCountInScene, testData.expectedNumbersInScene(),
                String.format("Expected 0 received %d", elementsCountInScene));

        Assert.assertNull(screen.exists(elementPattern), "Product is still on a screen.");
    }
}
