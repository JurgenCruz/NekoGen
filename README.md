# NekoGen

This is a tool that generates a save file for the Android Easter Egg where you collect cats. It currently generates 1859 unique cats with unique names. It could generate more, but around 2k cats the app crashes.

## Usage

1. Clone this repo from your favorite terminal and run `cd NekoGen`.
2. Run `./gradlew run` (`gradlew.bat run` if you are in Windows).
3. Let it run for some minutes. The last cats are always hard to find!
4. The tool will generate a file in `./nekogen/build/run/mPrefs.xml`.
5. Transfer this file to your phone and copy it to `/data/data/com.android.egg/shared_prefs/mPrefs.xml`. You probably need root to do this though.
6. Reboot your phone or kill the Easter Egg process. Then use the "Toy" from the quick actions to attract a cat so it reloads the app, and you can see your cats!

> [!TIP]
> All cats will have a blue bowtie. You can change `Application.kt` and add other collar colors and bowtie combinations if you don't care for the color and style of collar. This will still generate the same amount of cats, but the search will be quicker since it won't filter out collar colors.

## License

This project is under the [GNU GPL-3.0](https://choosealicense.com/licenses/gpl-3.0/) license.

## Buy me a coffee

You can always buy me a coffee here:

[![PayPal](https://img.shields.io/badge/PayPal-Donate-blue.svg?logo=paypal&style=for-the-badge)](https://www.paypal.com/donate/?business=AKVCM878H36R6&no_recurring=0&item_name=Buy+me+a+coffee&currency_code=USD)
[![Ko-Fi](https://img.shields.io/badge/Ko--fi-Donate-blue.svg?logo=kofi&style=for-the-badge)](https://ko-fi.com/jurgencruz)