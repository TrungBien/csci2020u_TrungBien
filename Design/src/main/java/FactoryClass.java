public class FactoryClass {
    //Creates an object of a particular subclass when asked and returns object
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
