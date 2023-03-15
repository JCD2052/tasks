//package pages.menus;
//
//import aquality.selenium.elements.interfaces.ICheckBox;
//import aquality.selenium.elements.interfaces.IComboBox;
//import aquality.selenium.forms.Form;
//import models.CarInfo;
//import org.openqa.selenium.By;
//
//public class FilterMenu extends Form {
//    private final IComboBox minYearSelectBox = getElementFactory()
//            .getComboBox(By.id("year_year_min_select"), "Min year select box.");
//    private final IComboBox maxYearSelectBox = getElementFactory()
//            .getComboBox(By.id("year_year_max_select"), "Max year select box.");
//
//    public FilterMenu() {
//        super(By.xpath("//div[contains(@class, 'guided')]"), "Filter menu");
//    }
//
//    public void selectMinYear() {
//        minYearSelectBox.clickAndSelectByText("");
//    }
//
//    public void selectMaxYear() {
//        maxYearSelectBox.clickAndSelectByText("");
//    }
//
//    public void selectTrim(CarInfo carInfo) {
//        String formattedTrimInfo = carInfo.getTrimInfo().getStyle()
//                .replace(" ", "_");
//        String locatorTrimName = String.format("trim_%s-%s-%s",
//                carInfo.getMaker(), carInfo.getModel(), formattedTrimInfo);
//        ICheckBox trimCheckbox = getElementFactory()
//                .getCheckBox(By.id(locatorTrimName), "Trim Checkbox");
//        trimCheckbox.check();
//    }
//}
