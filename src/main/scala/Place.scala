/**
 * Places.
 *
 * @author  Yujian Zhang <yujian{dot}zhang[at]gmail(dot)com>
 *
 * License:
 *   GNU General Public License v2
 *   http://www.gnu.org/licenses/gpl-2.0.html
 * Copyright (C) 2014-2018 Yujian Zhang
 */

package net.whily.hgc

import net.whily.scasci.math.Field

object PlaceType extends Enumeration {
  type PlaceType = Value
  val Capital, Province, Prefecture, County, Town = Value
}

case class Place(lat: Double, lon: Double,
  ptype: PlaceType.PlaceType = PlaceType.Prefecture) {
  /** Approximately equal. */
  def ≈ (that: Place): Boolean = {
    val e = Field.fieldD
    e.≈(lat, that.lat) && e.≈(lon, that.lon) && (ptype == that.ptype)
  }
}

object Place {
  // "To be confirmed" means that check is needed to verify with 中国历史地图集.
  val places = """
    // 周共和
    漢靈帝中平二年 add 洛阳 34.631514 112.454681 Capital   // To be confirmed
        add 长安 34.266667 108.9 Prefecture        // To be confirmed
        add 武關 33.71 110.35 Town                           // To be confirmed
        add 函谷關 34.519467 110.869081 Town        // To be confirmed
        add 萧關 36.016667 106.25 Town        // To be confirmed
        add 大散關 34.35 107.366667 Town        // To be confirmed
        add 邺 36.333333 114.616667 Prefecture     // Should be 20 km sw of the county.
        add 许 34.035803 113.852478 Prefecture   // To be confirmed
        add 寿春 32.0 116.5 Prefecture            // Estimated
        add 鄄城 35.566667 115.5 Prefecture   // To be confirmed
        add 房陵 32.1 110.6 Prefecture   // To be confirmed
        add 上庸 32.316667 110.15 Prefecture   // To be confirmed
        add 潼关 34.486389 110.263611 Town    // To be confirmed
        add 合肥 31.85 117.266667 Prefecture   // To be confirmed
        add 濮阳 35.75 115.016667 Prefecture   // To be confirmed
        add 东阿 36.333333 116.25 Prefecture   // To be confirmed
        add 范 35.85 115.5 Prefecture  // To be confirmed

        // 雍州
        // 中國歷史地圖集/三国/雍州: 祁山=2/3礼县 + 1/3天水市
        //   礼县 34.100833 104.976944  天水市 34.576 105.709
        add 祁山 34.25922 105.22096 County
        // 中國歷史地圖集/三国/雍州: 上邽=天水市
        add 上邽 34.576 105.709 County

        // 司隸
        add 河内郡 35.1 113.4 Prefecture   // 懷縣/武陟县 To be confirmed
        add 河東郡 35.138333 111.220833 Prefecture   // 安邑/夏县 To be confirmed
        add 左馮翊 34.506 109.051 Prefecture   // 高陵縣/高陵縣 To be confirmed
        add 右扶風 34.2995 108.4905 Prefecture   // 槐里县/兴平市 To be confirmde
        add 郿縣(褒斜道) 34.27786 107.60493 County    // 眉县 To be confirmed

        // 冀州
        add 渤海郡(南皮) 38.033333 116.7 Prefecture   // 南皮/南皮县 To be confirmed

        // 豫州
        add 汝南郡 32.958056 114.64 Prefecture   // To be confirmed

        // 兖州
        add 泰山郡 36.204722 117.159444 Prefecture    // To be confirmed
        add 濟北國 36.8 116.766667 Prefecture    // To be confirmed
        add 陈留郡 34.8 114.3 Prefecture   // To be confirmed
        add 官渡 34.720967 114.014228 Town    // To be confirmed
        add 乌巢 35.3 114.2 Town    // To be confirmed
        add 黎陽縣 35.676111 114.55 County    // 浚县 To be confirmed
        add 白馬 35.466375 114.636289 Town    // 滑县 To be confirmed
        add 延津 35.3 114.2 County    // 延津 To be confirmed

        // 徐州
        add 广陵郡 32.4 119.416667 Prefecture    // To be confirmed
        add 下邳 34.316667 117.95 Province    // To be confirmed

        // 青州
        add 北海國 36.883333 118.733333 Prefecture   // To be confirmed
        add 平原郡 37.166667 116.433333 Prefecture   // 平原县/平原县 To be confirmed

        // 凉州
        add 武都郡 33.916667 105.666667 Prefecture    // 下辨县/成县 To be confirmed
        add 金城郡 36.3425 102.858889 Prefecture   // 允吾县/民和 To be confirmed
        add 隴西郡(狄道縣) 35.379422 103.8564 Prefecture   // 狄道縣/临洮县 To be confirmed

        // 并州
        add 上党郡 36.123889 112.876944 Prefecture    //  长子县/长子县 To be confirmed
        add 太原郡 37.866667 112.55 Prefecture   //  晋阳/太原市 To be confirmed
        add 云中郡 38.416667 112.733333 Prefecture   // 云中县/忻州市 To be confirmed
        add 雁门郡 39.3198 112.432 Prefecture   //  阴馆县/朔城区 To be confirmed
        add 朔方郡 40.327778 107.003056 Prefecture   //  临戎县/磴口县 To be confirmed

        // 幽州
        add 涿郡 39.486 115.974 Prefecture   // 涿县/涿州市 To be confirmed
        add 代郡 40.3675 113.753056 Prefecture   // 高柳县/阳高县 To be confirmed
        add 渔阳郡 40.374444 116.839444 Prefecture   // 渔阳县/密云县 To be confirmed
        add 辽西郡 41.533333 121.233333 Prefecture   // 阳乐县/义县 To be confirmed
        add 辽东郡 41.266667 123.183333 Prefecture   // 襄平县/辽阳市 To be confirmed
        add 玄菟郡 41.733333 125.033333 Prefecture   // 高句骊县/新宾 To be confirmed
        add 乐浪郡 39.019444 125.738056 Prefecture   // 朝鲜县/平壤 To be confirmed

        // 益州
        add 成都 30.658611 104.064722 Capital   // To be confirmed
        add 汉中郡 33.066667 107.016667 Prefecture   // To be confirmed
        add 定軍山 33.13756 106.664275 Town    // To be confirmed
        add 兴势山 33.216667 107.533333 Town    // 洋县 To be confirmed
        add 街亭 34.9071 105.69833 Town    // 秦安县??? To be confirme
        add 列柳城 33.91711 106.5204 Town    // 双石铺镇??? To be confirmed
        add 陈仓 34.35 107.366667 Prefecture   // 寶雞 To be confirmed
        add 汉 33.15 106.666667 Town    // 勉县 To be confirmed
        add 乐 32.15 107.316667 Town    // 城固县 To be confirmed
        add 子口 34.1645 108.9502 Town    // 长安区 To be confirmed
        add 午口 33.0475 108.23 Town    // 石泉縣 To be confirmed
        add 木门道 34.44066 105.4979 Town    // 牡丹镇 To be confirmed
        add 沓中 34 103.55 Prefecture   // 迭部县 To be confirmed
        add 阳平關 32.961353 106.055392 Town        // To be confirmed
        add 涪城 31.466667 104.683333 Prefecture    // To be confirmed
        add 葭萌 32.433333 105.816667 Prefecture   // To be confirmed
        add 绵竹 31.333333 104.2 Prefecture   // To be confirmed
        add 阴平 32.916667 104.766667 Prefecture   // To be confirmed
        add 剑阁 32.211505 105.573795 Prefecture   // 剑门关
        add 永安 31.0175 109.465 Prefecture   // To be confirmed
        add 巴郡 29.560454 106.5734 Prefecture   // 江州县/渝中区 To be confirmed
        add 广汉郡(雒) 30.99 104.25 Prefecture   // 雒县/广汉市 To be confirmed
        add 犍为郡 30.193333 103.866667 Prefecture   // 武阳县/彭山县 To be confirmed
        add 益州郡 24.668442 102.591053 Prefecture   // 滇池县/晋宁县 To be confirmed
        add 永昌郡 25.103889 99.158056 Prefecture   // 不韋縣/保山市? To be confirmed
        add 牂柯郡 26.573056 106.706111 Prefecture   // 故且蘭縣/貴陽市 To be confirmed
        add 越巂郡 27.894444 102.264444 Prefecture   // 邛都縣/西昌市 To be confirmed

        add 建业 32.05 118.766667 Capital    // To be confirmed
        add 濡须 31.678333 117.735278 Prefecture    // To be confirmed
        add 庐陵 27.133333 115 Prefecture   // To be confirmed
        add 江夏 30.554722 114.312778 Prefecture   // To be confirmed

        // 荆州
        add 襄阳 32.066667 112.083333 Prefecture   // To be confirmed
        add 南阳 33.004722 112.5275 Prefecture    // To be confirmed
        add 新野縣 32.52 112.36 County    // 新野縣 To be confirmed
        add 當陽 30.975278 112.334444 County    // 荆门市掇刀区. Different from 中國歷史地圖集: to be confirmed
        add 宜城 31.7 112.366667 County    // 宜城市 (中國歷史地圖集/三国/荆州)
        add 夷陵 30.766667 111.316667 County    // To be confirmed
        add 夷道 30.383333 111.45 County    // 宜都 (Marked by 中國歷史地圖集/三国/荆州)
        add 猇亭 30.535833 111.423056 Town    // 猇亭区 (Estimated from 中國歷史地圖集/三国/荆州)
        add 赤壁 29.72647 113.93091 Town    // To be confirmed
        add 烏林 30.016667 113.533333 Town    // 洪湖市 To be confirmed
        add 麥城 30.70029 111.92649 Town    // 两河镇 (当阳市) To be confirmed
        add 武陵郡 29.0035 111.6928 Prefecture   // 临沅县 To be confirmed
        add 长沙郡 28.196111 112.972222 Prefecture   // To be confirmed
        add 桂阳郡 25.8 113.05 Prefecture   // To be confirmed
        add 零陵郡 25.616667 110.666667 Prefecture    // To be confirmed
        add 江陵 30.133333 112.5 Prefecture        // To be confirmed
        add 秭歸縣 30.832677 110.978394 County        // 秭歸縣 To be confirmed
        add 巫縣 31.0769 109.878216 County         // 巫山縣 To be confirmed
        add 華容縣 30.4 112.9 Prefecture        // 潜江市 To be confirmed

        // 扬州
        add 丹阳郡 30.945 118.814167 Prefecture   // To be confirmed
        add 庐江郡 31.259898 117.307838 Prefecture    // 舒县 To be confirmed
        add 会稽郡 30.081944 120.494722 Prefecture    // To be confirmed
        add 吴郡 31.3 120.6 Prefecture    //  To be confirmed
        add 豫章郡 28.683333 115.883333 Prefecture    // 南昌 To be confirmed
        add 柴桑縣 29.738056 115.987222 Prefecture    // 九江市 To be confirmed
        add 巴丘 29.366667 113.433333 Prefecture   // 岳阳 To be confirmed

        // 交州
        add 南海郡 23.128795 113.258976 Prefecture   // 番禺县/广州市 To be confirmed
        add 苍梧郡 23.45 111.466667 Prefecture    // 广信县/封开县 To be confirmed
        add 郁林郡 22.633333 110.15 Prefecture   // 布山县/玉林市 To be confirmed
        add 合浦郡 21.675 109.193056 Prefecture    // 合浦县/合浦县 To be confirmed
        add 交趾郡 21.183333 106.05 Prefecture    // 龙编县/北宁市? To be confirmed
        add 九真郡 19.78 105.71 Prefecture  // 胥浦县/东山县 To be confirmed
        add 日南郡 16.830278 107.097222 Prefecture   // 西卷县/東河市 To be confirmed

    唐高祖武德二年 clear
        // Province to be confirmed.
        add 華州 34.45 109.75 Prefecture // 治所在鄭縣（今華縣） To be confirmed
        add 同州 34.783611 109.976667 Prefecture // 治所在武鄉縣（今陝西省大荔縣） To be confirmed
        add 陝州 34.716667 111.1 Prefecture // 治所在陝縣（今河南省三門峽市陝州區）  To be confirmed
        add 虢州 34.525 110.866667 Prefecture // 治所在弘農縣（今河南省靈寶市）   To be confirmed
        add 蒲州 34.83444 110.3245 Prefecture // 治所在河東縣（今山西省永濟市西南蒲州鎮）  To be confirmed
        // 京兆府
        add 长安 34.266667 108.9 Capital        // To be confirmed
        add 奉天 34.496944 108.248056 County // 縣（今陝西乾縣）

        // 鳳翔節度使
        add 鳳翔 34.56 107.420556 Prefecture // 府治所在雍縣（今陝西鳳翔） To be confirmed

        // 涇原節度使， 後稱彰義軍節度使
        add 涇州 35.332 107.353 Prefecture // 治所在安定縣（今甘肅涇川） To be confirmed
        add 原州 36.016667 106.25 Prefecture // 治所在平高縣（今寧夏固原） To be confirmed

        // 邠寧節度使
        add 邠州 35.054167 108.081389 Prefecture // 治所在新平縣（今陝西彬縣） To be confirmed
        add 寧州 35.5375 108.155278 Prefecture // 治今甘肅寧縣  To be confirmed
        add 慶州 35.739 107.632 Prefecture // 治今甘肅慶陽  To be confirmed

        // 鄜坊節度使
        add 鄜州 35.983333 109.133333 Prefecture // 治所在洛交縣（今陝西富縣） To be confirmed
        add 坊州 35.579722 109.263056 Prefecture // 治所在中部縣（今陝西黃陵） To be confirmed
        add 丹州 36.099444 110.128333 Prefecture // 治今陝西宜川  To be confirmed
        add 延州 36.5855 109.4897 Prefecture // 治今陝西省延安市東北  To be confirmed

        // 朔方節度使
        add 靈州 38.1 106.333333 Prefecture // 治所在回樂縣（今寧夏吳忠） To be confirmed
        add 夏州 38.000239 108.851124 Prefecture // 治所在朔方縣(今陝西省靖邊縣北白城子)   To be confirmed
        add 鹽州 37.449722 107.828889 Prefecture // 治所在五原縣（今陝西省定邊縣）  To be confirmed
        add 綏州 37.506944 110.258889 Prefecture // 治所在上縣（今綏德縣）  To be confirmed
        add 銀州 37.96466 109.82087 Prefecture // 治所在儒林縣（今陝西省橫山縣東黨岔鎮） To be confirmed
        add 豐州 41.09 108.266111 Prefecture // 內蒙古自治區五原縣南?? To be confirmed
        add 勝州 39.866667 111.233333 Prefecture // 治所在榆林縣（今內蒙古自治區准格爾旗東北十二連城 GPS coordinates is for 准格爾旗）  To be confirmed
        add 麟州 38.819444 110.489722 Prefecture // 治今陝西神木  To be confirmed
        add 西受降城 40.765961 107.391206 Town // 治今內蒙古巴彥淖爾市內  To be confirmed
        add 中受降城 40.633333 109.983333 Town // 治今內蒙古包頭市內  To be confirmed
        add 東受降城 40.816667 111.65 Town // 治今內蒙古呼和浩特市內  To be confirmed

        // 荊南節度使
        add 江陵 30.133333 112.5 Prefecture // 府, 即荊州，治所在江陵縣（今湖北省江陵縣）
        add 忠州 30.3283 108.047 Prefecture // 治所在臨江縣（今重慶市忠縣）

        // 湖南觀察使
        add 潭州 28.196111 112.972222 Prefecture // 治所在長沙（今長沙）
        add 邵州 27.25 111.466667 Prefecture // 治所在邵陽縣（今湖南省邵陽市）
        add 武岡 26.359167 110.310833 County // 治所在武冈縣，属邵州（今湖南省城步苗族自治縣）Place moved??
        add 衡州 26.9 112.6 Prefecture // 治所在衡陽縣（今湖南省衡陽市）
        add 永州 26.2215 111.6169 Prefecture // 治所在零陵縣（今湖南省永州市），GPS data from 零陵區
        add 郴州 25.8 113.05 Prefecture // 治所在郴縣（今湖南省郴州市）

        // 振武軍節度使
        add 單于都護府 40.44943 111.78640 Prefecture
          // （今內蒙古和林格爾北的土城子古城， searched on maps.bing.com for 古城子遗址 then looked up in http://dbsgeo.com/latlon/）

        add 洛阳 34.631514 112.454681 Prefecture   // To be confirmed

        // 昭義節度使，又名澤潞節度使
        add 邢州 37.07 114.505 Prefecture // 治今河北邢臺 To be confirmed
        add 洺州 36.744 114.544 Prefecture // 治今河北永年東南 To be confirmed
        add 磁州 36.366667 114.383333 Prefecture // 治今河北磁縣 To be confirmed
        add 澤州 35.4895 112.8516 Prefecture // 治今山西晉城 To be confirmed
        add 潞州 36.1953 113.1163 Prefecture  // 治今山西長治市 To be confirmed

        // 義武軍節度使
        add 定州 38.516 114.99 Prefecture // 治今河北定州市 To be confirmed
        add 易州 39.349 115.498 Prefecture // 治今河北易縣 To be confirmed

        // 宣武軍節度使
        add 汴州 34.7975 114.3079 Prefecture // 治今今河南省開封市 To be confirmed
        add 宋州 34.4146 115.6558 Prefecture // 治今河南省商丘市 To be confirmed
        add 亳州 33.845 115.778 Prefecture // 治今安徽省亳州市 To be confirmed
        add 潁州 32.902222 115.83 Prefecture // 治今安徽省阜陽市潁州區 To be confirmed

        // 忠武軍節度使
        add 許州 34.036 113.852 Prefecture // 治今河南省許昌市 To be confirmed
        add 陳州 33.733056 114.85 Prefecture // 治今河南省淮陽縣 To be confirmed

        // 山南東道節度使
        add 襄州 32.066667 112.083333 Prefecture // 治今湖北省襄阳 To be confirmed
        add 鄧州 32.719133 112.083892 Prefecture // 治今河南省鄧州市 To be confirmed
        add 隨州 31.6901 113.3825 Prefecture // 治今湖北省隨州 To be confirmed
        add 唐州 32.731111 113.326944 Prefecture // 治今河南省泌陽縣 To be confirmed
        add 安州 31.266667 113.666667 Prefecture // 治今湖北省安陸市 To be confirmed
        add 房州 32.1 110.6 Prefecture // 治今湖北省房縣 To be confirmed
        add 均州 32.564175 111.512603 Prefecture // 治今湖北省房丹江口市 To be confirmed
        add 金州 32.6847 109.029 Prefecture // 治今陝西省安康市 To be confirmed
        add 商州 33.825556 109.997778 Prefecture // 治今陝西省商洛市商州區 To be confirmed

        // 魏博節度使
        add 魏州 36.28954 115.11817 Prefecture // 治今河北大名縣 To be confirmed
        add 博州 36.2729  115.5909  Prefecture // 治今山東聊城市 To be confirmed
        add 相州 36.1     114.333333 Prefecture // 治今河南安陽市 To be confirmed
        add 貝州 37.06938 115.65908 Prefecture // 治今河北清河縣 To be confirmed
        add 衛州 35.461786 113.805389 Prefecture // 治今河南輝縣市 To be confirmed
        add 澶州 35.75    115.016667 Prefecture // 治今河南濮陽市 To be confirmed

        // 淮西節度使
        add 蔡州 33.007068 114.362412 Prefecture // 治今河南汝南 To be confirmed
        add 申州 32.131783 114.071128 Prefecture // 治今河南信陽 To be confirmed
        add 光州 32.146111 115.138056 Prefecture // 治今河南潢川 To be confirmed

        // 成德節度使
        add 恆州 38.148561 114.561653 Prefecture // 治今河北正定縣 To be confirmed
        add 冀州 37.551 115.579 Prefecture // 治今河北冀州市  To be confirmed
        add 趙州 37.746389 114.768889 Prefecture // 治今河北趙縣  To be confirmed
        add 深州 38.002 115.559 Prefecture // 治今河北深州市  To be confirmed

        // 幽州節度使
        add 幽州 39.916667 116.383333 Prefecture // 治今北京 To be confirmed
        add 檀州 40.374444 116.839444 Prefecture // 治今北京密雲  To be confirmed
        add 薊州 40.036389 117.396944 Prefecture // 治今天津薊縣  To be confirmed
        add 媯州 40.416667 115.516667 Prefecture // 治今河北懷來  To be confirmed
        add 涿州 39.486 115.974 Prefecture // 治今河北涿州  To be confirmed
        add 莫州 38.712 116.099 Prefecture // 治今河北任丘北  To be confirmed
        add 瀛州 38.447 116.099 Prefecture // 治今河北河間  To be confirmed
        add 平州 39.9 118.9 Prefecture // 治今河北盧龍  To be confirmed
        add 營州 41.433333 120.166667 Prefecture // 治今遼寧朝陽，唐末遷至今河北昌黎  To be confirmed

        // 淄青節度使
        add 青州 36.696667 118.479722 Prefecture // 治今山東青州市  To be confirmed
        add 淄州 36.643611 117.966944 Prefecture // 治今山東淄川  To be confirmed
        add 齊州 36.633333 117.016667 Prefecture // 治今山東濟南市  To be confirmed
        add 沂州 35.05 118.316667 Prefecture // 治今山東臨沂  To be confirmed
        add 密州 35.995278 119.406667 Prefecture // 治今山東諸城  To be confirmed
        add 海州 34.6 119.166667 Prefecture // 治今江蘇連雲港西南海州鎮  To be confirmed
        add 登州 37.8 120.75 Prefecture // 治今山東蓬萊  To be confirmed
        add 萊州 37.179167 119.933333 Prefecture // 治今山東萊州市  To be confirmed
        add 曹州 35.233333 115.433333 Prefecture // 治今山東菏澤  To be confirmed
        add 濮州 35.566667 115.5 Prefecture // 治今山東鄄城  To be confirmed
        add 兗州 35.55 116.783333 Prefecture // 治今山東兗州市  To be confirmed
        add 鄆州 35.908333 116.3 Prefecture // 治今山東東平 To be confirmed

        // 隴右節度使
        add 鄯州 36.516667 102.416667 Prefecture //  治今青海省海東市樂都區 To be confirmed
        add 秦州 34.580833 105.724167 Prefecture //  治今甘肅省天水市秦州區 To be confirmed
        add 河州 35.601 103.211 Prefecture //  治今甘肅省臨夏 To be confirmed
        add 渭州 35.083333 104.65 Prefecture //  治今甘肅省隴西縣 To be confirmed
        add 蘭州 36.016667 103.866667 Prefecture //   甘肅省蘭州市 To be confirmed
        //add 臨州  Prefecture // 治今甘肅省臨洮縣???  To be confirmed
        add 武州 33.217 105.146 Prefecture // 治今甘肅省武都  To be confirmed
        add 洮州 34.7 103.666667 Prefecture // 治今甘肅省臨潭縣  To be confirmed
        add 岷州 35.379422 103.8564 Prefecture // 治今甘肅省臨洮縣??  To be confirmed
        add 廓州 36.04015 101.433298 Prefecture // 治今青海省貴德縣  To be confirmed
        add 疊州 34 103.55 Prefecture // 治今甘肅省迭部縣  To be confirmed
        add 宕州 33.633333 104.333333 Prefecture // 治今甘肅省舟曲縣  To be confirmed

        // 河西節度使
        add 涼州 37.928 102.641 Prefecture // 治今武威市  Confirmed
        add 甘州 39.014444 100.665833 Prefecture // 治今甘肅省張掖市 Confirmed
        add 肅州 39.740986 98.503418 Prefecture // 治今甘肅省酒泉市 Confirmed
        add 瓜州 40.2467 96.2027 Prefecture // 治今甘肅省瓜州縣鎖陽城遺址 Confirmed
        add 沙州 40.139246 94.644500 Prefecture // 治今敦煌沙州城遺址 Confirmed

        // 北庭節度使
        add 庭州 44.0683584 89.2119097 Prefecture //  治今吉木萨尔县城北12公里北庭乡,北庭故城. Confirmed
        add 伊州 42.8306 93.505 Prefecture // 治今新疆维吾尔自治区哈密市伊州区 Confirmed
        add 西州 42.856405 89.5264445 Prefecture // 治今吐鲁番市东南高昌故城  Confirmed

        // 安西節度使
        add 龟兹 41.566667 82.95 Prefecture // 治今库车 Confirmed
        add 焉耆 41.9670519 86.4605719 Prefecture // 治今博格达沁故城  Confirmed
        add 疏勒 39.466667 75.983333 Prefecture // 治今喀什市  Confirmed
        add 于阗 37.100404 79.7724963 Prefecture // 治今和田西部的约特干 (Not consistent with 中国历史地图集)
        add 碎叶 42.805222 75.199889 Prefecture // 即今阿克·贝希姆遗址，位於今吉尔吉斯斯坦楚河州托克馬克市西南8公里处. To be confirmed
        add 孽多城 35.920833 74.308333 Town  // 小勃律首都 治今巴基斯坦吉爾吉特.  Confirmed
        add 怛羅斯 42.525 72.233333 Town  // 接近哈薩克斯坦的塔拉茲.  Confirmed

        // 河東節度使
        add 太原 37.869444 112.560278 Prefecture // 治今山西太原  To be confirmed
        add 忻州 38.4163 112.7341 Prefecture // 治今山西忻州  To be confirmed
        add 代州 39.063611 112.943056 Prefecture // 治今山西代縣  To be confirmed
        add 嵐州 38.279444 111.671111 Prefecture // 治今山西嵐縣  To be confirmed
        add 汾州 37.266667 111.783333 Prefecture // 治今山西汾陽縣  To be confirmed
        add 遼州 37.081389 113.380278 Prefecture // 治今山西左權縣  To be confirmed
        add 沁州 36.500556 112.338611 Prefecture // 治今山西沁源縣  To be confirmed
        add 石州 37.52 111.13 Prefecture // 治今山西呂梁市離石區  To be confirmed
        add 憲州 38.068611 111.7975 Prefecture // 治今山西婁煩縣  To be confirmed

        // 劍南節度使
        add 益州 30.659722 104.063333 Prefecture // 治今四川省成都市
        add 維州 31.416667 103.166667 Prefecture // 今四川省理縣東北  To be confirmed

        // 鎮海軍節度使
        add 润州 32.188 119.428 Prefecture // 治今江苏省镇江市  To be confirmed
        add 杭州 30.266667 120.2 Prefecture // 治今浙江省杭州市  To be confirmed
        add 常州 31.81 119.976 Prefecture // 治今江苏省常州市  To be confirmed
        add 苏州 31.5 120.616667 Prefecture // 治今江苏省苏州市  To be confirmed
        add 湖州 30.894 120.087 Prefecture // 治今浙江省湖州市  To be confirmed
        add 昇州 32.043889 118.778611 Prefecture // 治今江苏省南京市  To be confirmed
        add 宣州 30.945 118.814167 Prefecture // 治今安徽省宣城市  To be confirmed
        add 歙州 29.87 118.42 Prefecture // 治今安徽省歙縣  To be confirmed
        add 饶州 28.993333 116.679444 Prefecture // 治今江西省鄱陽縣  To be confirmed
        add 江州 29.7054 116.0026 Prefecture // 治今江西省九江市  To be confirmed

        // 嶺南節度使
        // 廣管
        add 廣州 23.128795 113.258976 Prefecture // 治今廣東省廣州市  To be confirmed
        // 容管
        add 容州 22.5168 110.482 Prefecture // 治今廣西北流市  To be confirmed
        // 邕管
        add 邕州 22.8161 108.3664 Prefecture // 治今廣西南寧市 (coordinates is based on current 南寧市 Should be 治所在宣化县（今广西壮族自治区南宁市南）in Tang dynasty
        add 橫州 22.689 109.271 Prefecture // 治今廣西橫縣  To be confirmed
        add 欽州 21.98 108.654 Prefecture // 治今廣西欽州市  To be confirmed
        add 賓州 23.222222 108.805556 Prefecture // 治今今廣西賓陽縣  To be confirmed
        // 桂管
        add 桂州 25.2344 110.1799 Prefecture // 治今廣西桂林市  To be confirmed
        add 連州 24.783333 112.383333 Prefecture // 治今廣東連州市  To be confirmed
        add 柳州 24.3262 109.4284 Prefecture // 治今廣西柳州市  To be confirmed
        // 交管
        add 交州 21.028472 105.854167 Prefecture // 治今越南河內市  To be confirmed

        // 吐蕃
        add 邏些城 29.65 91.1 Capital // 治今拉薩. Confirmed

        // add   Prefecture // 治今  To be confirmed
    // 宋太祖建隆
    公元二〇〇〇年 del 武關
        ren 華州 華縣 // To be confirmed
        ren 同州 大荔 // To be confirmed
        ren 陝州 三門峽市 // 三門峽市陝州區 To be confirmed
        ren 虢州 靈寶市 // To be confirmed
        ren 蒲州 永濟市 // 永濟市西南蒲州鎮 To be confirmed
        update 长安 34.266667 108.9 Province // To be confirmed
        ren 长安 西安
        ren 奉天 乾縣 // To be confirmed
        ren 涇州 涇川 // To be confirmed
        ren 原州 固原 // To be confirmed
        ren 邠州 彬縣 // To be confirmed
        ren 寧州 寧縣 // To be confirmed
        ren 慶州 慶陽 // To be confirmed
        ren 鄜州 富縣 // To be confirmed
        ren 坊州 黃陵 // To be confirmed
        ren 丹州 宜川 // To be confirmed
        ren 延州 延安市 // 延安市東北  To be confirmed
        ren 靈州 吳忠 // To be confirmed
        ren 夏州 靖邊 // 靖邊縣北白城子  To be confirmed
        ren 鹽州 定邊 //  To be confirmed
        ren 綏州 綏德 //  To be confirmed
        ren 銀州 橫山 // 橫山縣東黨岔鎮） To be confirmed
        ren 豐州 五原 //五原縣南?? To be confirmed
        ren 勝州 准格爾旗 // 准格爾旗東北十二連城 GPS coordinates is for 准格爾旗）  To be confirmed
        ren 麟州 神木 // To be confirmed
        ren 西受降城 巴彥淖爾市 // To be confirmed
        ren 中受降城 包頭 // To be confirmed
        ren 東受降城 呼和浩特 // To be confirmed
        ren 忠州 忠縣 // To be confirmed
        ren 潭州 長沙
        ren 邵州 邵陽
        ren 武岡 城步
        ren 衡州 衡陽
        ren 單于都護府 和林格爾 // 和林格爾北的土城子古城， searched on maps.bing.com for 古城子遗址 then looked up in http://dbsgeo.com/latlon/）
        ren 魏州 大名 // To be confirmed
        ren 博州 聊城 // To be confirmed
        ren 相州 安陽 // To be confirmed
        ren 貝州 清河 // To be confirmed
        ren 衛州 輝縣 // To be confirmed
        ren 澶州 濮陽 // To be confirmed
        ren 蔡州 汝南 // To be confirmed
        ren 申州 信陽 // To be confirmed
        ren 光州 潢川 // To be confirmed
        ren 恆州 正定 // To be confirmed
        ren 趙州 趙縣 // To be confirmed
        update 幽州 39.916667 116.383333 Capital
        ren 幽州 北京
        ren 檀州 密雲 //  To be confirmed
        ren 薊州 薊縣 // To be confirmed
        ren 媯州 懷來 // To be confirmed
        ren 莫州 任丘 // 任丘北  To be confirmed
        ren 瀛州 河間 // To be confirmed
        ren 平州 盧龍 // To be confirmed
        ren 營州 昌黎 // 治今遼寧朝陽，唐末遷至今河北昌黎  To be confirmed
        ren 淄州 淄川 // To be confirmed
        ren 齊州 濟南 // To be confirmed
        ren 沂州 臨沂 // To be confirmed
        ren 密州 諸城 // To be confirmed
        ren 海州 連雲港 // 治今江蘇連雲港西南海州鎮  To be confirmed
        ren 登州 蓬萊 // To be confirmed
        ren 曹州 菏澤 // To be confirmed
        ren 濮州 鄄城 // To be confirmed
        ren 鄆州 東平 // To be confirmed
        ren 鄯州 海東 //  治今青海省海東市樂都區 To be confirmed
        ren 秦州 天水 //  治今甘肅省天水市秦州區 To be confirmed
        ren 河州 臨夏 // To be confirmed
        ren 渭州 隴西 // To be confirmed
        ren 武州 武都 // To be confirmed
        ren 洮州 臨潭 // To be confirmed
        ren 岷州 臨洮 // To be confirmed
        ren 廓州 貴德 // To be confirmed
        ren 疊州 迭部 // To be confirmed
        ren 宕州 舟曲 // To be confirmed
        ren 涼州 武威
        ren 甘州 張掖
        ren 肅州 酒泉
        // update 瓜州 甘肅省瓜州縣鎖陽城遺址 Confirmed
        ren 沙州 敦煌 // 敦煌沙州城遺址 Confirmed
        ren 庭州 吉木萨尔 // 吉木萨尔县城北12公里北庭乡,北庭故城. Confirmed
        ren 伊州 哈密 // 哈密市伊州区 Confirmed
        ren 西州 吐鲁番 // 吐鲁番市东南高昌故城  Confirmed
        ren 龟兹 库车
        ren 焉耆 博格达沁故城
        ren 疏勒 喀什
        ren 于阗 和田 // 和田西部的约特干 (Not consistent with 中国历史地图集)
        ren 碎叶 托克馬克 // 即今阿克·贝希姆遗址，位於今吉尔吉斯斯坦楚河州托克馬克市西南8公里处. To be confirmed
        ren 孽多城 吉爾吉特
        ren 怛羅斯 塔拉茲
        ren 代州 代縣 //  To be confirmed
        ren 嵐州 嵐縣  // To be confirmed
        ren 汾州 汾陽 // To be confirmed
        ren 遼州 左權 //  To be confirmed
        ren 沁州 沁源 // To be confirmed
        ren 石州 呂梁 // 治今山西呂梁市離石區  To be confirmed
        ren 憲州 婁煩 // To be confirmed
        ren 益州 成都
        ren 維州 理縣 // 理縣東北  To be confirmed
        ren 润州 镇江 // To be confirmed
        ren 昇州 南京
        ren 宣州 宣城
        ren 歙州 歙縣
        ren 饶州 鄱陽
        ren 江州 九江
        ren 容州 北流
        ren 邕州 南寧 // 南寧市 (coordinates is based on current 南寧市 Should be 治所在宣化县（今广西壮族自治区南宁市南）in Tang dynasty
        ren 橫州 橫縣
        ren 賓州 賓陽
        ren 桂州 桂林
        update 交州 21.028472 105.854167 Capital
        ren 交州 河內
        ren 邏些城 拉薩
  """
}
