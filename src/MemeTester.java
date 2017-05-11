/**
 * Created by Aaron on 5/9/2017.
 */
public class MemeTester {
    public static void main(String[] args) {
        Meme myMeme = new Meme("Test", "C:\\Users\\Aaron\\Desktop\\memeGenerator\\src\\image.jpg");
        myMeme.saveAsFile("test");
    }
}
