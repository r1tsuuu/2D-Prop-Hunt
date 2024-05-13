package game;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import math.Vector2;

public class SeekerScene extends GameCanvas {

    Seeker player;
    Camera camera;
    OtherCharacter other;
    String perspective;
    Gun gun;
    Timer timer;

    public SeekerScene(GameFrame frame) {
        super(frame);
    }

    @Override
    public void ready() {

        player = new Seeker(new Vector2(928, 560));
        other = new OtherCharacter("Hider", new Vector2(928, 560), 16, 16, "assets\\props.png", 0, 5);
        camera = new Camera(player.getPosition(), this, Vector2.multiply(player.getSize(), -0.5f), 2f);
        player.setCamera(camera);
        gun = new Gun("Pistol", player.getPosition(), "assets\\gun.png", (Seeker) player, this, other);
        timer = new Timer(player.getPosition());

        add(camera);
        add(new ImageObject("map", Vector2.ZERO, "assets\\map.png"));
        addWalls();
        add(new Tracker(player, other));
        add(player);
        add(other);
        add(gun);

        addNetworkInObject(timer);
        add(timer.getHund());
        add(timer.getTens());
        add(timer.getOnes());
    
    }

    @Override
    public void networkNotified(String input) {
        if (input.equals("victory") || input.equals("defeat"))
            getFrame().endGame(input);
        else if (input.charAt(0) == 'd') {
            var values = input.split(" ");
            float posX = Float.parseFloat(values[1]);
            float posY = Float.parseFloat(values[2]);
            int xFrame = Integer.parseInt(values[3]);
            add(new Sprite("assets\\props.png", 16, 16, xFrame, 0, new Vector2(posX, posY)));
        }
    }

    private void addWalls() {
        add(new TestWall(new Vector2(11, 11), new Vector2(16, 1205)));
        add(new TestWall(new Vector2(11, 11), new Vector2(1861, 16)));
        add(new TestWall(new Vector2(1856, 11), new Vector2(1861, 1205)));
        add(new TestWall(new Vector2(11, 1201), new Vector2(1861, 1205)));

        add(new TestWall(new Vector2(864, 16), new Vector2(880, 511)));
        add(new TestWall(new Vector2(527, 496), new Vector2(880, 511)));
        add(new TestWall(new Vector2(512, 240), new Vector2(528, 511)));
        add(new TestWall(new Vector2(512, 16), new Vector2(368, 511)));

        add(new TestWall(new Vector2(16, 496), new Vector2(368, 511)));
        add(new TestWall(new Vector2(352, 240), new Vector2(368, 511)));
        add(new TestWall(new Vector2(352, 16), new Vector2(368, 192)));
        add(new TestWall(new Vector2(512, 16), new Vector2(528, 192)));

        add(new TestWall(new Vector2(880, 16), new Vector2(976, 48)));

        add(new TestWall(new Vector2(975, 16), new Vector2(992, 496)));
        add(new TestWall(new Vector2(976, 496), new Vector2(1344, 511)));
        add(new TestWall(new Vector2(1327, 240), new Vector2(1344, 512)));
        add(new TestWall(new Vector2(1327, 16), new Vector2(1344, 193)));

        add(new TestWall(new Vector2(1488, 16), new Vector2(1504, 193)));
        add(new TestWall(new Vector2(1487, 240), new Vector2(1504, 511)));
        add(new TestWall(new Vector2(1487, 496), new Vector2(1856, 511)));

        add(new TestWall(new Vector2(16, 624), new Vector2(369, 640)));
        add(new TestWall(new Vector2(352, 624), new Vector2(369, 877)));
        add(new TestWall(new Vector2(352, 928), new Vector2(369, 1200)));
        add(new TestWall(new Vector2(511, 624), new Vector2(528, 880)));
        add(new TestWall(new Vector2(511, 928), new Vector2(528, 1200)));
        add(new TestWall(new Vector2(511, 624), new Vector2(880, 640)));
        add(new TestWall(new Vector2(864, 624), new Vector2(880, 1200)));

        add(new TestWall(new Vector2(976, 624), new Vector2(992, 1200)));
        add(new TestWall(new Vector2(976, 624), new Vector2(1345, 640)));
        add(new TestWall(new Vector2(1488, 624), new Vector2(1855, 640)));
        add(new TestWall(new Vector2(1488, 624), new Vector2(1504, 897)));
        add(new TestWall(new Vector2(1488, 944), new Vector2(1504, 1200)));
        add(new TestWall(new Vector2(1327, 624), new Vector2(1345, 897)));
        add(new TestWall(new Vector2(1327, 944), new Vector2(1345, 1200)));

        add(new TestWall(new Vector2(880, 1168), new Vector2(976, 1200)));

        add(new TestWall(new Vector2(416, 480), new Vector2(421, 512)));
        add(new TestWall(new Vector2(459, 480), new Vector2(463, 512)));
        add(new TestWall(new Vector2(368, 507), new Vector2(421, 512)));
        add(new TestWall(new Vector2(459, 507), new Vector2(512, 512)));
        add(new TestWall(new Vector2(16, 128), new Vector2(80, 133)));
        add(new TestWall(new Vector2(91, 16), new Vector2(96, 96)));
        add(new TestWall(new Vector2(91, 91), new Vector2(144, 96)));
        add(new TestWall(new Vector2(91, 91), new Vector2(144, 96)));
        add(new TestWall(new Vector2(176, 91), new Vector2(229, 96)));
        add(new TestWall(new Vector2(224, 16), new Vector2(229, 96)));
        add(new TestWall(new Vector2(16, 347), new Vector2(64, 352)));
        add(new TestWall(new Vector2(96, 347), new Vector2(144, 352)));
        add(new TestWall(new Vector2(144, 347), new Vector2(149, 496)));
        add(new TestWall(new Vector2(176, 395), new Vector2(181, 496)));
        add(new TestWall(new Vector2(208, 395), new Vector2(352, 401)));
        add(new TestWall(new Vector2(299, 171), new Vector2(352, 192)));
        add(new TestWall(new Vector2(299, 240), new Vector2(352, 261)));

        add(new TestWall(new Vector2(528, 144), new Vector2(708, 148)));
        add(new TestWall(new Vector2(705, 16), new Vector2(708, 110)));
        add(new TestWall(new Vector2(737, 108), new Vector2(863, 111)));
        add(new TestWall(new Vector2(528, 321), new Vector2(704, 324)));
        add(new TestWall(new Vector2(705, 321), new Vector2(708, 335)));
        add(new TestWall(new Vector2(705, 364), new Vector2(708, 399)));
        add(new TestWall(new Vector2(705, 364), new Vector2(719, 367)));
        add(new TestWall(new Vector2(705, 433), new Vector2(708, 496)));
        add(new TestWall(new Vector2(753, 364), new Vector2(863, 367)));

        add(new TestWall(new Vector2(992, 188), new Vector2(1140, 191)));
        add(new TestWall(new Vector2(1137, 145), new Vector2(1140, 191)));
        add(new TestWall(new Vector2(1137, 16), new Vector2(1140, 115)));
        add(new TestWall(new Vector2(1140, 113), new Vector2(1150, 116)));
        add(new TestWall(new Vector2(1185, 113), new Vector2(1327, 116)));
        add(new TestWall(new Vector2(992, 364), new Vector2(1135, 367)));
        add(new TestWall(new Vector2(1169, 364), new Vector2(1328, 367)));

        add(new TestWall(new Vector2(1344, 508), new Vector2(1396, 511)));
        add(new TestWall(new Vector2(1393, 481), new Vector2(1396, 511)));
        add(new TestWall(new Vector2(1436, 481), new Vector2(1439, 511)));
        add(new TestWall(new Vector2(1436, 508), new Vector2(1488, 511)));

        add(new TestWall(new Vector2(1553, 16), new Vector2(1556, 95)));
        add(new TestWall(new Vector2(1553, 92), new Vector2(1631, 95)));
        add(new TestWall(new Vector2(1665, 92), new Vector2(1680, 95)));
        add(new TestWall(new Vector2(1681, 16), new Vector2(1684, 143)));
        add(new TestWall(new Vector2(1681, 176), new Vector2(1684, 191)));
        add(new TestWall(new Vector2(1681, 188), new Vector2(1856, 191)));
        add(new TestWall(new Vector2(1681, 241), new Vector2(1856, 244)));
        add(new TestWall(new Vector2(1681, 241), new Vector2(1684, 256)));
        add(new TestWall(new Vector2(1681, 289), new Vector2(1684, 496)));

        add(new TestWall(new Vector2(368, 625), new Vector2(420, 628)));
        add(new TestWall(new Vector2(417, 625), new Vector2(420, 655)));
        add(new TestWall(new Vector2(460, 625), new Vector2(463, 655)));
        add(new TestWall(new Vector2(460, 625), new Vector2(512, 628)));

        add(new TestWall(new Vector2(177, 640), new Vector2(180, 703)));
        add(new TestWall(new Vector2(177, 737), new Vector2(180, 831)));
        add(new TestWall(new Vector2(160, 753), new Vector2(176, 756)));
        add(new TestWall(new Vector2(177, 865), new Vector2(180, 879)));
        add(new TestWall(new Vector2(177, 876), new Vector2(303, 879)));
        add(new TestWall(new Vector2(336, 876), new Vector2(353, 879)));
        add(new TestWall(new Vector2(336, 876), new Vector2(353, 879)));
        add(new TestWall(new Vector2(177, 929), new Vector2(303, 932)));
        add(new TestWall(new Vector2(336, 929), new Vector2(353, 932)));
        add(new TestWall(new Vector2(177, 929), new Vector2(180, 944)));
        add(new TestWall(new Vector2(177, 977), new Vector2(180, 1200)));
        add(new TestWall(new Vector2(16, 1100), new Vector2(127, 1103)));
        add(new TestWall(new Vector2(16, 753), new Vector2(127, 756)));
        add(new TestWall(new Vector2(161, 1100), new Vector2(176, 1103)));

        add(new TestWall(new Vector2(705, 796), new Vector2(756, 799)));
        add(new TestWall(new Vector2(705, 796), new Vector2(708, 895)));
        add(new TestWall(new Vector2(784, 796), new Vector2(864, 799)));
        add(new TestWall(new Vector2(705, 929), new Vector2(708, 964)));
        add(new TestWall(new Vector2(673, 961), new Vector2(735, 964)));
        add(new TestWall(new Vector2(769, 961), new Vector2(863, 964)));
        add(new TestWall(new Vector2(609, 961), new Vector2(623, 964)));
        add(new TestWall(new Vector2(609, 961), new Vector2(612, 1200)));
        add(new TestWall(new Vector2(753, 640), new Vector2(756, 799)));

        add(new TestWall(new Vector2(1344, 625), new Vector2(1396, 628)));
        add(new TestWall(new Vector2(1393, 625), new Vector2(1396, 655)));
        add(new TestWall(new Vector2(1436, 625), new Vector2(1439, 655)));
        add(new TestWall(new Vector2(1436, 625), new Vector2(1488, 628)));

        add(new TestWall(new Vector2(992, 812), new Vector2(1055, 815)));
        add(new TestWall(new Vector2(1088, 812), new Vector2(1103, 815)));
        add(new TestWall(new Vector2(1100, 785), new Vector2(1103, 895)));
        add(new TestWall(new Vector2(1100, 945), new Vector2(1103, 1011)));
        add(new TestWall(new Vector2(992, 1009), new Vector2(1039, 1012)));
        add(new TestWall(new Vector2(1089, 1009), new Vector2(1119, 1012)));
        add(new TestWall(new Vector2(1153, 1009), new Vector2(1172, 1012)));
        add(new TestWall(new Vector2(1169, 1009), new Vector2(1172, 1022)));
        add(new TestWall(new Vector2(1200, 1009), new Vector2(1203, 1023)));
        add(new TestWall(new Vector2(1200, 1009), new Vector2(1327, 1012)));
        add(new TestWall(new Vector2(1201, 1057), new Vector2(1204, 1200)));
        add(new TestWall(new Vector2(1169, 1057), new Vector2(1172, 1200)));
        add(new TestWall(new Vector2(1100, 640), new Vector2(1103, 735)));
        add(new TestWall(new Vector2(1153, 640), new Vector2(1156, 735)));
        add(new TestWall(new Vector2(1201, 812), new Vector2(1328, 815)));

        add(new TestWall(new Vector2(1504, 881), new Vector2(1663, 884)));
        add(new TestWall(new Vector2(1697, 881), new Vector2(1856, 884)));
        add(new TestWall(new Vector2(1660, 640), new Vector2(1663, 847)));
        add(new TestWall(new Vector2(1697, 640), new Vector2(1700, 847)));
        add(new TestWall(new Vector2(1660, 993), new Vector2(1663, 1200)));
        add(new TestWall(new Vector2(1697, 993), new Vector2(1700, 1200)));
        add(new TestWall(new Vector2(1504, 956), new Vector2(1663, 959)));
        add(new TestWall(new Vector2(1697, 956), new Vector2(1856, 959)));
    }

}
