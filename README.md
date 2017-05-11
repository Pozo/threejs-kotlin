# puNk

## What is it?

Hiding a payload in PNG files with ~~Python~~ Java, according to Brian de Heus [article](http://blog.brian.jp/python/png/2016/07/07/file-fun-with-pyhon.html) and the [reddit](https://www.reddit.com/r/programming/comments/4rt266/hiding_a_payload_in_png_files_with_python/) link which led me there.

## Usage

```
git clone https://github.com/Pozo/punk && cd punk && mvn clean install

java -jar target/punk-1.0-SNAPSHOT.jar -e \
-s $HOME/source.png \
-i $HOME/inject.png \
-o output.png

```

## Example
####encode
Let's inject the latest zipped jQuery into a PNG file.
```
wget -O $HOME/spaceinvaders.png http://www.adiumxtras.com/images/thumbs/space_invaders_1_17601_6150_thumb.png
wget -O $HOME/jquery.zip https://github.com/jquery/jquery/archive/master.zip

java -jar target/punk-1.0-SNAPSHOT.jar -e \
-s $HOME/spaceinvaders.png \
-i $HOME/jquery.zip \
-o invader.png

#sudo apt-get install pngtools
pngchunks invader.png 
7z l invader.png

$$ profit $$
```
####decode
Let's recover jQuery from that little bastard.
```
java -jar target/punk-1.0-SNAPSHOT.jar -d \
-i invader.png \
-o jquery
```
## Banana for scale

![Meetup Flyer](https://github.com/Pozo/meetup-flyer/blob/master/flyer.png "Meetup Flyer")
## Licensing

Please see the file called LICENSE.

## Contact

  Zoltan Polgar - pozo@gmx.com
  
  Please do not hesitate to contact me if you have any further questions. 