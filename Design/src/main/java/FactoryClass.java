public class FactoryClass {

    public Phone getPhone(String phoneType){
        if (phoneType.equalsIgnoreCase("APPLE")){
            return new Iphone();
        } else if (phoneType.equalsIgnoreCase("SAMSUNG")){
            return new SamsungPhone();
        } else if (phoneType.equalsIgnoreCase("NOKIA")){
            return new NokiaPhone();
        } else {
            return null;
        }


    }
}
