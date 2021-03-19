import java.util.Locale;

public class CC {
    String id;
    String basekeyA = "";
    String basekeyB = "";
    String portA = "";
    String portB = "";
    public CC(String id, String basekeyA, String portA, String basekeyB, String portB) {
        this.id = id;
        this.basekeyA = basekeyA.toUpperCase();
        this.basekeyB = basekeyB.toUpperCase();
        this.portA = portA.toUpperCase();
        this.portB = portB.toUpperCase();
    }
    public CC(String basekeyA, String portA, String basekeyB, String portB) {
        this.basekeyA = basekeyA.toLowerCase();
        this.basekeyB = basekeyB.toLowerCase();
        this.portA = portA.toLowerCase();
        this.portB = portB.toLowerCase();
    }

    public static CC switchEnds(CC c) {
        if (c == null) return c;
        String tmp;
        tmp = c.basekeyA;
        c.basekeyA = c.basekeyB;
        c.basekeyB = tmp;
        tmp = c.portA;
        c.portA = c.portB;
        c.portB = tmp;
        return c;
    }

    public boolean addNextNode(CC next) {
        if (portB.equals(next.portA) && basekeyB.equals(next.basekeyA)) {
            return true;
        }
        next = switchEnds(next);
        if (portB.equals(next.portA) && basekeyB.equals(next.basekeyA)) {
            return true;
        }
        next = switchEnds(next);
        return false;
    }

    public String toString() {
        return String.format("%s:%s <--> %s:%s", portA, basekeyA, basekeyB, portB);
    }

    @Override
    public int hashCode() {
        return (basekeyA.concat(portA)).hashCode() * (basekeyB.concat(portB)).hashCode();
    }
}
