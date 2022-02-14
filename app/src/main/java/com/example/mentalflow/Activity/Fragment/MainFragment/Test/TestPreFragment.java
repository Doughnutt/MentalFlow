package com.example.mentalflow.Activity.Fragment.MainFragment.Test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentalflow.Activity.Activity.Initial.Test0ProActivity;
import com.example.mentalflow.R;

// 测试启动页：一共十五个测试
public class TestPreFragment extends Fragment {

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test_pre, container, false);
        Bundle b = getArguments();
        assert b != null;
        int test_category = b.getInt("test_category",0);
        int id = b.getInt("test_id"); //获取id
        String title = b.getString("test_title"); //获取标题
        String content = b.getString("test_content"); //获取内容

        // 设置背景
        Resources resources = requireContext().getResources();
        Drawable drawable = null;
        int queBg = 0;
        if (id == 1) {
            drawable = resources.getDrawable(R.mipmap.test1_bg1); //设置准备背景
            queBg = R.mipmap.test1_bg2; //问题背景
        } else if(id == 2) {
            drawable = resources.getDrawable(R.mipmap.test2_bg1);
            queBg = R.mipmap.test2_bg2;
        } else if(id == 3) {
            drawable = resources.getDrawable(R.mipmap.test3_bg1);
            queBg = R.mipmap.test3_bg2;
        } else if(id == 4) {
            drawable = resources.getDrawable(R.mipmap.test4_bg1);
            queBg = R.mipmap.test4_bg2;
        } else if(id == 5) {
            drawable = resources.getDrawable(R.mipmap.test5_bg1);
            queBg = R.mipmap.test5_bg2;
        } else if(id == 6) {
            drawable = resources.getDrawable(R.mipmap.test6_bg1);
            queBg = R.mipmap.test6_bg2;
        } else if(id == 7) {
            drawable = resources.getDrawable(R.mipmap.test7_bg1);
            queBg = R.mipmap.test7_bg2;
        } else if(id == 8) {
            drawable = resources.getDrawable(R.mipmap.test8_bg1);
            queBg = R.mipmap.test8_bg2;
        } else if(id == 9) {
            drawable = resources.getDrawable(R.mipmap.test9_bg1);
            queBg = R.mipmap.test9_bg2;
        } else if(id == 10) {
            drawable = resources.getDrawable(R.mipmap.test10_bg1);
            queBg = R.mipmap.test10_bg2;
        } else if(id == 11) {
            drawable = resources.getDrawable(R.mipmap.test11_bg1);
            queBg = R.mipmap.test11_bg2;
        } else if(id == 12) {
            drawable = resources.getDrawable(R.mipmap.test12_bg1);
            queBg = R.mipmap.test12_bg2;
        } else if(id == 13) {
            drawable = resources.getDrawable(R.mipmap.test13_bg1);
            queBg = R.mipmap.test13_bg2;
        } else if(id == 14) {
            drawable = resources.getDrawable(R.mipmap.test14_bg1);
            queBg = R.mipmap.test14_bg2;
        } else if(id == 15) {
            drawable = resources.getDrawable(R.mipmap.test15_bg1);
            queBg = R.mipmap.test15_bg2;
        }
        view.setBackground(drawable);

        // 显示内容
        TextView textView1 = (TextView) view.findViewById(R.id.test_pre_title);
        textView1.setText(title);
        TextView textView2 = (TextView) view.findViewById(R.id.test_pre_content);
        textView2.setText(content);
        if(id == 3 || id == 5 || id == 7 || id == 8 || id == 11) {
            int color = getResources().getColor(R.color.black); //必须这样设置颜色
            textView1.setTextColor(color);
            textView2.setTextColor(color);
        }

        // 准备数据
        int queSize = 0; //问题个数
        int optSize = 0; //选项个数
        String[] queList = new String[55]; //问题数组
        String[][] optList = new String[55][10]; //选项数组

        if (id == 1) { //领悟社会支持量表
            queList[0] = "在我遇到问题时有些人（领导、亲戚、同事）会出现在我的身旁。";
            queList[1] = "我能够与有些人（领导、亲戚、同事）共享快乐与忧伤。";
            queList[2] = "我的家庭能够切实具体地给我帮助。";
            queList[3] = "在需要时我能够从家庭获得感情上的帮助和支持。";
            queList[4] = "当我有困难时有些人（领导、亲戚、同事）是安慰我的真正源泉。";
            queList[5] = "我的朋友们能真正的帮助我。";
            queList[6] = "在发生困难时我可以依靠我的朋友们。";
            queList[7] = "我能与自己的家庭谈论我的难题。";
            queList[8] = "我的朋友们能与我分享快乐与忧伤。";
            queList[9] = "在我的生活中有些人（领导、亲戚、同事）关心着我的感情。";
            queList[10] = "我的家庭能心甘情愿协助我作出各种决定。";
            queList[11] = "我能与朋友们讨论自己的难题。";
            queSize = 12;
            // 问题中的选项
            optList[0][0] = "极同意";
            optList[0][1] = "很同意";
            optList[0][2] = "稍同意";
            optList[0][3] = "中立";
            optList[0][4] = "稍不同意";
            optList[0][5] = "很不同意";
            optList[0][6] = "极不同意";
            optSize = 7;
        }
        else if(id == 2) {//威廉斯创造力倾向测量表
            queList[0] = "在学校里，我喜欢试着对事情或问题作猜测，即使不一定猜对也无所谓。";
            queList[1] = "我喜欢仔细观察我没有见过的东西，以了解详细的情形。";
            queList[2] = "我喜欢变化多端和富有想象力的故事。";
            queList[3] = "画图时我喜欢临摹别人的作品。";
            queList[4] = "我喜欢利用旧报纸、旧日历及旧罐头盒等废物来做成各种好玩的东西。";
            queList[5] = "我喜欢幻想一些我想知道或想做的事。";
            queList[6] = "如果事情不能一次完成，我会继续尝试，直到完成为止。";
            queList[7] = "做功课时我喜欢参考各种不同的资料，以便得到多方面的了解。";
            queList[8] = "我喜欢用相同的方法做事情，不喜欢去找其他新的方法。";
            queList[9] = "我喜欢探究事情的真相。";
            queList[10] = "我喜欢做许多新鲜的事。";
            queList[11] = "我不喜欢交新朋友。";
            queList[12] = "我喜欢想一些不会在我身上发生的事。";
            queList[13] = "我喜欢想象有一天能成为艺术家、音乐家或诗人。";
            queList[14] = "我会因为一些令人兴奋的念头而忘了其他的事。";
            queList[15] = "我宁愿生活在太空站，也不愿生活在地球上。";
            queList[16] = "我认为所有问题都有固定答案。";
            queList[17] = "我喜欢与众不同的事情。";
            queList[18] = "我常想要知道别人正在想什么。";
            queList[19] = "我喜欢故事或电视节目所描写的事。";
            queList[20] = "我喜欢和朋友在一起，和他们分享我的想法。";
            queList[21] = "如果一本故事书的最后一页被撕掉了，我就自己编造一个故事，把结果补上去。";
            queList[22] = "我长大后，想做一些别人从没想过的事。";
            queList[23] = "尝试新的游戏和活动，是一件有趣的事。";
            queList[24] = "我不喜欢受太多规则限制。";
            queList[25] = "我喜欢解决问题，即使没有正确答案也没关系。";
            queList[26] = "有许多事情我都很想亲自去尝试。";
            queList[27] = "我喜欢唱没有人知道的新歌。";
            queList[28] = "我不喜欢在班上同学面前发表意见。";
            queList[29] = "当我读小说或看电视时，我喜欢把自己想成故事中的人物。";
            queList[30] = "我喜欢幻想200年前人类生活的情形。";
            queList[31] = "我常想自己编一首新歌。";
            queList[32] = "我喜欢翻箱倒柜，看看有些什么东西在里面。";
            queList[33] = "画图时，我很喜欢改变各种东西的颜色和形状。";
            queList[34] = "我不敢确定我对事情的看法都是对的。";
            queList[35] = "对于一件事情先猜猜看，然后再看是不是猜对了，这种方法很有趣。";
            queList[36] = "玩猜谜之类的游戏很有趣，因为我想知道结果如何。";
            queList[37] = "我对机器感兴趣，也很想知道它的里面是什么样子，以及它是怎样转动的。";
            queList[38] = "我喜欢可以拆开来玩的玩具。";
            queList[39] = "我喜欢想一些新点子，即使用不着也无所谓。";
            queList[40] = "一篇好的文章应该包含许多不同的意见或观点。";
            queList[41] = "为将来可能发生的问题找答案，是一件令人兴奋的事。";
            queList[42] = "我喜欢尝试新的事情，目的只是为了想知道会有什么结果。";
            queList[43] = "玩游戏时，我通常是有兴趣参加，而不在乎输赢。";
            queList[44] = "我喜欢想一些别人常常谈过的事情。";
            queList[45] = "当我看到一张陌生人的照片时，我喜欢去猜测他是怎么样的一个人。";
            queList[46] = "我喜欢翻阅书籍及杂志，但只想大致了解一下。";
            queList[47] = "我不喜欢探寻事情发生的各种原因。";
            queList[48] = "我喜欢问一些别人没有想到的问题。";
            queList[49] = "无论在家里还是在学校，我总是喜欢做许多有趣的事。";
            queSize = 50;
            // 问题中的选项
            optList[0][0] = "完全符合";
            optList[0][1] = "部分适合";
            optList[0][2] = "完全不符";
            optSize = 3;
        }
        else if(id == 3) { //情绪紧张度测试
            queList[0] = "常常毫无原因地觉得心烦意乱、坐立不安。";
            queList[1] = "临睡时仍在思虑各种问题，不能安寝。即使睡着，也容易被惊醒。";
            queList[2] = "肠胃功能紊乱，经常腹泻。";
            queList[3] = "容易做噩梦，一到晚上就倦怠无力，焦虑烦躁。";
            queList[4] = "一有不称心的事情，便大量吸烟，抑郁寡欢、沉默少言。";
            queList[5] = "早晨起床后，就有倦怠感，头昏脑涨，浑身没劲，爱静怕动，消沉。";
            queList[6] = "经常没有食欲，吃东西没有味道，宁可忍受饥饿。";
            queList[7] = "稍微运动，就会出现心跳加速、胸闷气急。";
            queList[8] = "不管在哪儿，都感到有许多事情不称心，暗自烦躁。";
            queList[9] = "想得到某样东西，一时不能满足就会感到心中难受。";
            queList[10] = "偶尔做一点轻便工作，就会感到疲劳、周身乏力。";
            queList[11] = "出门做事的时候，总觉得精力不济、有气无力。";
            queList[12] = "当着亲友的面，稍不如意，就会勃然大怒，失去理智。";
            queList[13] = "任何一件小事，都会始终盘桓在脑海里，整天思索。";
            queList[14] = "处理事情唯我独尊，情绪急躁，态度粗暴。";
            queList[15] = "一喝酒就过量，意识和潜意识里都想一醉方休。";
            queList[16] = "对别人的病患，非常关心，到处打听，唯恐自己身患同病。";
            queList[17] = "看到别人成功或获得赞誉，常会嫉妒，甚至怀恨在心。";
            queList[18] = "置身繁杂的环境里，容易思维杂乱、行为失序。";
            queList[19] = "左邻右舍家中发出的噪音，会使你感到焦躁发慌，心悸出汗。";
            queList[20] = "明知是愚不可及的事情，却非做不可，事后又感到懊悔。";
            queList[21] = "即使是消闲读物也看不进去，甚至连中心思想也搞不清楚。";
            queList[22] = "一有空就整天打麻将，混一天是一天。";
            queList[23] = "经常和同事或家人甚至陌生人发生争吵。";
            queList[24] = "经常感到头疼胸闷，有缺氧的感觉。";
            queList[25] = "每每陷入往事便追悔莫及，有负疚感。";
            queList[26] = "做事说话都急不可待，措辞激烈。";
            queList[27] = "遇到突发事件就失去信心，显得焦虑紧张。";
            queList[28] = "性格倔强固执，脾气急躁，不易合群。";
            queSize = 29;
            // 问题中的选项
            optList[0][0] = "有";
            optList[0][1] = "无";
            optSize = 2;
        }
        else if(id == 4) { //情绪—社交孤独测试
            queList[0] = "我没有挚友";
            queList[1] = "跟别人一道时，人家想占我的便宜";
            queList[2] = "我没有伴侣（男／女朋友）";
            queList[3] = "我不愿因自己的困难而让别人感到有负担";
            queList[4] = "在我的生活中没有人依赖我";
            queList[5] = "任何人跟我都不交心";
            queList[6] = "生活中没有人想要了解我";
            queList[7] = "生活中没有人愿意受到我的连累";
            queList[8] = "我有许多时间独自呆着";
            queList[9] = "我未加入任何社团或组织";
            queList[10] = "我今天跟任何人都未说话";
            queList[11] = "我跟周围的人没有共同话题可谈";
            queList[12] = "与别人相处时我并不更多地坦露自己";
            queList[13] = "我不冒社交之险";
            queList[14] = "人们不觉得我有趣";
            queList[15] = "我没觉得有挚友";
            queList[16] = "我害怕相信别人";
            queList[17] = "我没觉得我有伴侣（男／女朋友）";
            queList[18] = "当分担我的困难时，我的好友觉得是个负担";
            queList[19] = "我觉得别人不依赖我，也不觉得我重要";
            queList[20] = "我觉得我无法跟任何人交心";
            queList[21] = "我觉得不被理解";
            queList[22] = "我觉得求别人并不安全";
            queList[23] = "我感到孤独";
            queList[24] = "我不觉得是任何社团或组织中的一员";
            queList[25] = "我觉得今天跟任何人都没接触";
            queList[26] = "我觉得与别人无话可说";
            queList[27] = "我觉得跟别人相处时不再是本来的我";
            queList[28] = "与别人相处时我感到难堪";
            queList[29] = "我不觉得自己有趣";
            queSize = 30;
            // 问题中的选项
            optList[0][0] = "通常如此";
            optList[0][1] = "经常如此";
            optList[0][2] = "有时如此";
            optList[0][3] = "偶尔如此";
            optSize = 4;
        }
        else if(id == 5) { //情绪稳定性测试
            queList[0] = "看到自己最近一次拍摄的照片，你有何想法？";
            queList[1] = "你是否想到若干年后会有什么使自己极为不安的事？";
            queList[2] = "你是否被同学起过绰号、挖苦过？";
            queList[3] = "你上床以后，是否经常再起来一次，看看门窗是否关好，煤气是否关好等？";
            queList[4] = "你对与你关系最密切的人是否满意？";
            queList[5] = "你在半夜的时候，是否经常觉得有什么值得害怕的事？";
            queList[6] = "你是否经常因梦见什么可怕的事而惊醒？";
            queList[7] = "你是否曾经有多次做同一个梦的情况？";
            queList[8] = "有没有一种食物使你吃后呕吐？";
            queList[9] = "除去看见的世界外，你心里有没有另外一种世界？";
            queList[10] = "你心里是否时常觉得你不是现在父母所生的？";
            queList[11] = "你是否曾经觉得有一个人爱你或尊重你？";
            queList[12] = "你是否常常觉得你的家庭对你不好，但是你又确知他们的确对你很好？";
            queList[13] = "你是否觉得没有人十分了解你？";
            queList[14] = "你在早晨起来的时候最经常的感觉是什么？";
            queList[15] = "每到秋天，你经常的感觉是什么？";
            queList[16] = "你在高处的时候，是否觉得站不稳？";
            queList[17] = "你平时是否觉得自己很强健？";
            queList[18] = "你是否一回家就立刻把房门关上？";
            queList[19] = "你坐在小房间里把门关上后，是否觉得心里不安？";
            queList[20] = "当一件事需要你做决定时，你是否觉得很难？";
            queList[21] = "你是否常常用抛硬币、玩纸牌、抽签之类的游戏来测凶吉？";
            queList[22] = "你是否常常因碰到东西而跌倒？";
            queList[23] = "你是否需用一个多小时才能入睡，或醒得比你希望的早一个小时？";
            queList[24] = "你是否曾看到、听到或感觉到别人觉察不到的东西？";
            queList[25] = "你是否觉得自己有超越常人的能力？";
            queList[26] = "你是否曾经觉得有人跟着你走而心里不安？";
            queList[27] = "你是否觉得友人在注意你的言行？";
            queList[28] = "当你一个人走夜路时，是否觉得浅藏着危险？";
            queList[29] = "你对别人自杀有什么想法？";
            queSize = 30;
            // 问题中的选项
            optList[0][0] = "觉得不称心";
            optList[0][1] = "觉得很好";
            optList[0][2] = "觉得可以";
            optList[1][0] = "经常想到";
            optList[1][1] = "从来没有想到";
            optList[1][2] = "偶尔想到过";
            optList[2][0] = "常有的事";
            optList[2][1] = "从来没有";
            optList[2][2] = "偶尔有过";
            optList[3][0] = "经常如此";
            optList[3][1] = "从不如此";
            optList[3][2] = "偶尔如此";
            optList[4][0] = "不满意";
            optList[4][1] = "非常满意";
            optList[4][2] = "基本满意";
            optList[5][0] = "经常";
            optList[5][1] = "从来没有";
            optList[5][2] = "极少有这种情况";
            optList[6][0] = "经常";
            optList[6][1] = "没有";
            optList[6][2] = "极少";
            for(int i=7;i<11;i++) {
                optList[i][0] = "有";
                optList[i][1] = "没有";
                optList[i][2] = "记不清";
            }
            optList[11][0] = "有";
            optList[11][1] = "没有";
            optList[11][2] = "说不清";
            optList[12][0] = "是";
            optList[12][1] = "否";
            optList[12][2] = "偶尔";
            optList[13][0] = "是";
            optList[13][1] = "否";
            optList[13][2] = "说不清";
            optList[14][0] = "忧郁";
            optList[14][1] = "快乐";
            optList[14][2] = "说不清";
            optList[15][0] = "秋雨霏霏或枯叶遍地";
            optList[15][1] = "秋高气爽或艳阳天";
            optList[15][2] = "不清楚";
            optList[16][0] = "有";
            optList[16][1] = "否";
            optList[16][2] = "有时是这样";
            optList[17][0] = "否";
            optList[17][1] = "是";
            optList[17][2] = "不清楚";
            optList[18][0] = "是";
            optList[18][1] = "否";
            optList[18][2] = "不清楚";
            for(int i=19;i<23;i++) {
                optList[i][0] = "是";
                optList[i][1] = "否";
                optList[i][2] = "偶尔是";
            }
            optList[23][0] = "经常这样";
            optList[23][1] = "从不这样";
            optList[23][2] = "偶尔这样";
            optList[24][0] = "经常这样";
            optList[24][1] = "从不这样";
            optList[24][2] = "偶尔这样";
            optList[25][0] = "是";
            optList[25][1] = "否";
            optList[25][2] = "偶尔是";
            optList[26][0] = "是";
            optList[26][1] = "否";
            optList[26][2] = "不清楚";
            optList[27][0] = "是";
            optList[27][1] = "否";
            optList[27][2] = "不清楚";
            optList[28][0] = "是";
            optList[28][1] = "否";
            optList[28][2] = "偶尔是";
            optList[29][0] = "可以理解";
            optList[29][1] = "不可思议";
            optList[29][2] = "不清楚";
            optSize = 3;
        }
        else if(id == 6) { //处世能力测试
            queList[0] = "在聚餐会上，如果你与多数同桌的人素不相识，你怎么办？";
            queList[1] = "觉得自己与协同工作的人在性格和想法方面合不来，你怎么办？";
            queList[2] = "在公共汽车上，你无意踩别人一脚，别人对你骂个不停，你怎么办？";
            queList[3] = "在影剧院看电影时，你的邻座旁若无人地讲话，使你感到讨厌，你怎么办？";
            queList[4] = "你辛苦地干完工作，自以为干得很不错，不料领导很不满意，你怎么办？";
            queList[5] = "你买了一架崭新的照相机，自己还未用过，但有朋友向你借，你怎么办？";
            queList[6] = "当你正埋头干一件急事时，一位朋友上门来找你倾诉苦恼，你怎么办？";
            queList[7] = "在你知道了别人的一些隐私之后，你怎么办？";
            queList[8] = "星期天，你忙了一整天，把房间全部打扫干净，你的爱人下班回家后，却指责你没及时烧晚饭，你怎么办？";
            queList[9] = "当你搬到一个新的住处，周围邻居都不认识，显得较冷淡，你怎么办？";
            queList[10] = "如果有人经常要麻烦你做一些事，你却很忙，你怎么办？";
            queList[11] = "一位朋友向你借了几元钱，但后来没还，好像不记得这回事了，你怎么办?";
            queList[12] = "在餐馆你买了一份饭菜，但发现味道太咸，你怎么办？";
            queList[13] = "一位热情的售货员为了使你买到满意东西，向你介绍了这些东西，但你都不满意，你怎么办？";
            queSize = 14;
            // 问题中的选项
            optList[0][0] = "显得心神不定，左顾右盼";
            optList[0][1] = "静听别人的谈话";
            optList[0][2] = "只与相识的人高谈阔论";
            optList[0][3] = "神态自如地参与大家的谈论";
            optList[1][0] = "委屈就全，尽量凑合下去";
            optList[1][1] = "故意找事由，与他吵架，迫使领导解决";
            optList[1][2] = "向领导汇报他的短处，要求领导调离他";
            optList[1][3] = "尽量谅解，实在不行，向领导如实说明，等候机会解决";
            optList[2][0] = "只当没听见，任他去骂";
            optList[2][1] = "与他对骂，不惜大吵一架";
            optList[2][2] = "推说别人挤了自己才踩到他的，不应该怪罪自己";
            optList[2][3] = "请他原谅，同时提醒他骂人是不文明的";
            optList[3][0] = "希望别人能出面向他们提意见或他们自己停止";
            optList[3][1] = "严厉地指责他们";
            optList[3][2] = "叫服务员来制止他们";
            optList[3][3] = "有礼貌地请他们别讲话";
            optList[4][0] = "不作声地听领导埋怨，但心中十分委屈";
            optList[4][1] = "拂袖而去，认为自己不应受埋怨";
            optList[4][2] = "解释说因客观条件限制，自己无法做得更好";
            optList[4][3] = "注意自己做得不够的地方，以便今后改正";
            optList[5][0] = "给他，但是满腹牢骚";
            optList[5][1] = "脸色很难看，使得朋友不得不改口";
            optList[5][2] = "骗他说已经借给别人了";
            optList[5][3] = "告诉他自己要试拍一下，检查了照相机的性能后再借给他";
            optList[6][0] = "放下手中工作，耐心倾听";
            optList[6][1] = "很不耐烦，流露处不想听的神态";
            optList[6][2] = "似听非听，脑子里还在想自己的事情";
            optList[6][3] = "向他解释，同他另约时间";
            optList[7][0] = "觉得好奇，但绝不传给其他人听";
            optList[7][1] = "忍不住，会很快告诉其他人";
            optList[7][2] = "当其他人谈论起的时候，也会附和着一起谈";
            optList[7][3] = "根本没有想要让其他人也知道";
            optList[8][0] = "心理很气，但仍勉强地去烧饭";
            optList[8][1] = "发脾气，骂爱人自私，要爱人自己去烧饭";
            optList[8][2] = "气的当晚不吃饭";
            optList[8][3] = "向爱人解释，然后邀请爱人一起出去吃饭";
            optList[9][0] = "尽量避免与邻居交往";
            optList[9][1] = "故意显出自己是很强硬的，让人家有种敬畏感";
            optList[9][2] = "视邻居以后对自己的态度再行事";
            optList[9][3] = "主动与邻居打招呼，表现出友好的姿态";
            optList[10][0] = "尽量避开他";
            optList[10][1] = "静听别人的谈话";
            optList[10][2] = "只与相识的人高谈阔论";
            optList[10][3] = "神态自如地参与大家的谈论";
            optList[11][0] = "今后再也不借钱给他";
            optList[11][1] = "提醒他曾借过钱";
            optList[11][2] = "向他借同等数额的钱，作为抵消";
            optList[11][3] = "就当没这回事";
            optList[12][0] = "向同桌人发牢骚";
            optList[12][1] = "粗略地责骂厨师无能";
            optList[12][2] = "默默地吃下去";
            optList[12][3] = "平静地问服务员，能否变淡些，如不能，则吃不下去";
            optList[13][0] = "买一件你并不想买的东西";
            optList[13][1] = "说这些商品质量不好，是卖不掉的商品";
            optList[13][2] = "向他道歉，说是朋友托买的东西，一定要朋友满意的才能买";
            optList[13][3] = "说一声谢谢，然后离去";
            optSize = 4;
        }
        else if(id == 7) { //交往焦虑量表(IAS)
            queList[0] = "即使在非正式的聚会上，我也常感到紧张。";
            queList[1] = "与一群不认识的人在一起，我通常感到不自在。";
            queList[2] = "在与一位异性交谈时我通常感到轻松。";
            queList[3] = "在必须同老师或上司交谈时，我感到紧张。";
            queList[4] = "聚会常使我感到焦虑及不自在。";
            queList[5] = "与大多数人相比，我在社会交往中较少羞怯。";
            queList[6] = "在与我不太熟悉的同性谈话时，我常常感到紧张。";
            queList[7] = "在求职面试时我会紧张的。";
            queList[8] = "我希望自己在社交场合自信心更足一些。";
            queList[9] = "在社交场合中，我很少感到焦虑。";
            queList[10] = "一般而言，我是一个害羞的人。";
            queList[11] = "在与一位迷人异性交谈时我会经常感到紧张的。";
            queList[12] = "在给不太熟悉的人打电话时我会显得紧张。";
            queList[13] = "我在与权威人士谈话时感到紧张。";
            queList[14] = "即使处于一群和我相当不同的人群之中，通常我仍感到放松。";
            queSize = 15;
            // 问题中的选项
            optList[0][0] = "本条与我极其符合";
            optList[0][1] = "本条与我非常符合";
            optList[0][2] = "本条与我中等程度符合";
            optList[0][3] = "本条与我有一点儿符合";
            optList[0][4] = "本条与我一点也不符合";
            optSize = 5;
        }
        else if(id == 8) { //社会适应能力测试量表
            queList[0] = "我最怕转学、转班级、换单位，因为每到一个新环境，我总要经过很长一段时间才能适应。";
            queList[1] = "每到一个新的地方，我很容易同别人接近。";
            queList[2] = "在陌生人面前，我常无话可说，以至感到尴尬。";
            queList[3] = "我最喜欢学习新知识、新技术，它给我一种新鲜感，能调动我的积极性。";
            queList[4] = "每到一个新地方，我第一天总是睡不好，就是在家里，只要换一张床，有时也会失眠。";
            queList[5] = "不管生活条件有多大变化，我也能很习惯。";
            queList[6] = "越是人多的地方，我越感到紧张。";
            queList[7] = "在正式比赛或考试时，我的成绩多半会比平时差。";
            queList[8] = "我最怕在会上讲话，大家都看着我，心都快跳出来了。";
            queList[9] = "即使同学、同事对我有看法，我仍能正常同他（她）交往。";
            queList[10] = "老师、领导在场的时候，我做事情总有些不自在。";
            queList[11] = "和同学、同事、家人相处，我很少固执己见，乐于采纳别人的看法。";
            queList[12] = "同别人争论时，我常常感到语塞，事后才想起该怎样反驳对方，可惜已经太迟了。";
            queList[13] = "我对生活条件要求不高，即使生活条件和艰苦，我也能过得很愉快。";
            queList[14] = "有时自己明明把考试内容背得滚瓜烂熟，可在考试上还是会出差错。";
            queList[15] = "在决定胜负成败的关键时刻，我虽然很紧张，但总能很快地使自己镇定下来。";
            queList[16] = "我不喜欢的东西，不管怎么学也学步会。";
            queList[17] = "在嘈杂混乱的环境里，我仍能集中精力学习或工作，并且效率不减。";
            queList[18] = "我不喜欢陌生人来家里做客，每逢这种情况，我就有意回避。";
            queList[19] = "我很喜欢参加社交活动，我感到这是交朋友的好机会。";
            queSize = 20;
            // 问题中的选项
            optList[0][0] = "同意";
            optList[0][1] = "不确定";
            optList[0][2] = "不同意";
            optSize = 3;
        }
        else if(id == 9) { //社交回避及苦恼量表(SAD)
            queList[0] = "即使在不熟悉的社交场合里我仍感到放松。";
            queList[1] = "我尽量避免迫使我参加交际应酬的情形。";
            queList[2] = "我同陌生人在一起时很容易放松。";
            queList[3] = "我并不特别想去回避人们。";
            queList[4] = "我通常发现社交场合令人心烦意乱。";
            queList[5] = "在社交场合我通常感觉平静及舒适。";
            queList[6] = "在同异性交谈时，我通常感觉放松。";
            queList[7] = "我尽量避免与别人讲话，除非特别熟。";
            queList[8] = "如果有同新人相会的机会，我会抓住的。";
            queList[9] = "在非正式的聚会上如有异性参加，我通常觉得焦虑和紧张。";
            queList[10] = "我通常与人们在一起时感到焦虑，除非与他们特别熟。";
            queList[11] = "我与一群人在一起时通常感到放松。";
            queList[12] = "我经常想离开人群。";
            queList[13] = "在置身于不认识的人群中时，我通常感到不自在。";
            queList[14] = "在初次遇见某些人时，我通常是放松的。";
            queList[15] = "被介绍给别人时，我会感到紧张和焦虑。";
            queList[16] = "尽管满房间都是生人，我可能还是会进去的。";
            queList[17] = "我会避免走进去并加入到一大群人中间。";
            queList[18] = "当上司想同我谈话时，我很高兴与他谈话。";
            queList[19] = "当与一群人在一起时，我通常感觉忐忑不安。";
            queList[20] = "我喜欢躲开人群。";
            queList[21] = "在晚上或社交聚会上与人们交谈对我不成问题。";
            queList[22] = "在一大群人中间，我极少能感到自在。";
            queList[23] = "我经常想出一些借口以回避社交活动。";
            queList[24] = "我有时充当为人们相互介绍的角色。";
            queList[25] = "我尽量避开正式的社交场合。";
            queList[26] = "我通常参加我所能参加的各种社会交往。不管是什么社交活动，我一般是能去就去。";
            queList[27] = "我发现同他人在一起时放松很容易。";
            queSize = 28;
            // 问题中的选项
            optList[0][0] = "是";
            optList[0][1] = "否";
            optSize = 2;
        }
        else if(id == 10) { //信任量表(TS)
            queList[0] = "在我们这个社会里虚伪的现象越来越多了。 ";
            queList[1] = "与陌生人打交道时，你最好小心，除非他们拿出可以证明其值得信任的依据。";
            queList[2] = "除非我们吸引更多的人进人政界，这个国家的前途将十分黯淡。";
            queList[3] = "阻止多数人触犯法律的是恐惧、社会廉耻或惩罚而不是良心。";
            queList[4] = "考试时老师不到场监考可能会导致更多的人作弊。";
            queList[5] = "通常父母在遵守诺言方面是可以信赖的。";
            queList[6] = "联合国永远也不会成为维持世界和平的有效力量。";
            queList[7] = "法院是我们都能受到公正对待的场所。";
            queList[8] = "如果得知公众听到和看到的新闻有多少已被歪曲，多数人会感到展惊的。";
            queList[9] = "不管人们怎样表白，最好还是认为多数人主要关心其自身幸福。";
            queList[10] = "尽管在报纸、收音机和电视中均可看到新闻，但我们很难得到关于公共事件的客观报道。";
            queList[11] = "未来似乎很有希望。";
            queList[12] = "如果真正了解到国际上正在发生的政治事件，那么公众有理由比现在更加担心。";
            queList[13] = "多数获选官员在竞选中的许诺是诚恳的。";
            queList[14] = "许多重大的全国性体育比赛均受到某种形式的操纵和利用。";
            queList[15] = "多数专家有关其知识局限性的表白是可信的。";
            queList[16] = "多数父母关于实施惩罚的威胁是可信的。";
            queList[17] = "多数人如果说出自己的打算就一定会去实现。";
            queList[18] = "在这个竞争的年代里，如果不保持警惕别人就可能占你的便宜．";
            queList[19] = "多数理想主义者是诚恳的并按照他们自己所宣扬的信条行事。";
            queList[20] = "多数推销人员在描述他们的产品时是诚实的。";
            queList[21] = "多数学生即使在有把握不会被发现时也不作弊．";
            queList[22] = "多数维修人员即使认为你不位其专业知识也不会多收费。";
            queList[23] = "对保险公司的控告有相当一部分是假的。 ";
            queList[24] = "多数人诚实地回答民意测验中的问题。";
            queSize = 25;
            // 问题中的选项
            optList[0][0] = "完全同意";
            optList[0][1] = "部分同意";
            optList[0][2] = "同意与不同意相等";
            optList[0][3] = "部分不同意";
            optList[0][4] = "完全不同意";
            optSize = 5;
        }
        else if(id == 11) { //贝克抑郁自评量表
            for(int i=0;i<21;i++) {
                queList[i] = "下列选项中最符合你的是";
            }
            queSize = 21;
            // 问题中的选项
            optList[0][0] = "我不感到悲伤";
            optList[0][1] = "我感到悲伤";
            optList[0][2] = "我始终悲伤，不能自制";
            optList[0][3] = "我太悲伤或不愉快，不堪忍受";
            optList[1][0] = "我对将来并不失望";
            optList[1][1] = "对未来我感到心灰意冷";
            optList[1][2] = "我感到前景黯淡";
            optList[1][3] = "我觉得将来毫无希望，无法改善";
            optList[2][0] = "我没有感到失败";
            optList[2][1] = "我觉得比一般人失败要多些";
            optList[2][2] = "回首往事，我能看到的是很多次失败";
            optList[2][3] = "我觉得我是一个完全失败的人";
            optList[3][0] = "我从各种事件中得到很多满足";
            optList[3][1] = "我不能从各种事件中感受到乐趣";
            optList[3][2] = "我不能从各种事件中得到真正的满足";
            optList[3][3] = "我对一切事情不满意或感到枯燥无味";
            optList[4][0] = "我不感到有罪过";
            optList[4][1] = "我在相当的时间里感到有罪过";
            optList[4][2] = "我在大部分时间里觉得有罪";
            optList[4][3] = "我在任何时候都觉得有罪";
            optList[5][0] = "我没有觉得受到惩罚";
            optList[5][1] = "我觉得可能会受到惩罚";
            optList[5][2] = "我预料将受到惩罚";
            optList[5][3] = "我觉得正受到惩罚";
            optList[6][0] = "我对自己并不失望";
            optList[6][1] = "我对自己感到失望";
            optList[6][2] = "我讨厌自己";
            optList[6][3] = "我恨自己";
            optList[7][0] = "我觉得并不比其他人更不好";
            optList[7][1] = "我要批判自己的弱点和错误";
            optList[7][2] = "我在所有的时间里都责备自己的错误";
            optList[7][3] = "我责备自己把所有的事情都弄坏了";
            optList[8][0] = "我没有任何想弄死自己的想法";
            optList[8][1] = "我有自杀想法，但我不会去做";
            optList[8][2] = "我想自杀";
            optList[8][3] = "如果有机会我就自杀";
            optList[9][0] = "我哭泣与往常一样";
            optList[9][1] = "我比往常哭得多";
            optList[9][2] = "我一直要哭";
            optList[9][3] = "我过去能哭，但要哭也哭不出来";
            optList[10][0] = "和过去相比，我生气并不更多";
            optList[10][1] = "我比往常更容易生气发火";
            optList[10][2] = "我觉得所有的时间都容易生气";
            optList[10][3] = "过去使我生气的事，目前一点也不能使我生气了";
            optList[11][0] = "我对其他人没有失去兴趣";
            optList[11][1] = "和过去相比，我对别人的兴趣减少了";
            optList[11][2] = "我对别人的兴趣大部分失去了";
            optList[11][3] = "我对别人的兴趣已全部丧失了";
            optList[12][0] = "我作出决定没什么困难";
            optList[12][1] = "我推迟作出决定比过去多了";
            optList[12][2] = "我作决定比以前困难大得多";
            optList[12][3] = "我再也不能作出决定了";
            optList[13][0] = "觉得我的外表看上去并不比过去更差";
            optList[13][1] = "我担心自己看上去显得老了，没有吸引力";
            optList[13][2] = "我觉得我的外貌有些变化，使我难看了";
            optList[13][3] = "我相信我看起来很丑陋";
            optList[14][0] = "我工作和以前一样好";
            optList[14][1] = "要着手做事，我目前需额外花些力气";
            optList[14][2] = "无论做什么我必须努力催促自己才行";
            optList[14][3] = "我什么工作也不能做了";
            optList[15][0] = "我睡觉与往常一样好";
            optList[15][1] = "我睡眠不如过去好";
            optList[15][2] = "我比往常早醒1~2小时，难以再睡";
            optList[15][3] = "我比往常早醒几个小时，不能再睡";
            optList[16][0] = "我并不感到比往常更疲乏";
            optList[16][1] = "我比过去更容易感到疲乏无力";
            optList[16][2] = "几乎不管做什么，我都感到疲乏无力";
            optList[16][3] = "我太疲乏无力，不能做任何事情";
            optList[17][0] = "我的食欲和往常一样";
            optList[17][1] = "我的食欲不如过去好";
            optList[17][2] = "我目前的食欲差得多了";
            optList[17][3] = "我一点也没有食欲了";
            optList[18][0] = "最近我的体重并无很大减轻";
            optList[18][1] = "我体重下降2.27 千克以上";
            optList[18][2] = "我体重下降5.54 千克以上";
            optList[18][3] = "我体重下降7.81 千克以上";
            optList[19][0] = "我对健康状况并不比往常更担心";
            optList[19][1] = "我担心身体上的问题，如疼痛、胃不适或便秘";
            optList[19][2] = "我很担心身体问题，想别的事情很难";
            optList[19][3] = "我对身体问题如此担忧，以致不能想其他任何事情";
            optList[20][0] = "我没有发现自己对性的兴趣最近有什么变化";
            optList[20][1] = "我对性的兴趣比过去降低了";
            optList[20][2] = "我现在对性的兴趣大大下降";
            optList[20][3] = "我对性的兴趣已经完全丧失";
            optSize = 4;
        }
        else if(id == 12) { //心理承受能力测试
            queList[0] = "当你与父母发生不愉快时，你是否曾想离家出走?";
            queList[1] = "如果现在就去睡觉，你会担心自己会睡不着吗?";
            queList[2] = "晚睡两个小时会使你第二天明显地精神不振吗?";
            queList[3] = "看完惊险片后，在很长一段时间内，你一直觉得心有余悸吗?";
            queList[4] = "你常常觉得生活很累吗?";
            queList[5] = "当考试成绩不理想时，你会感到非常沮丧吗?";
            queList[6] = "当你与某个同学闹意见后，你一直无法消除相处时的尴尬吗?";
            queList[7] = "当你在课堂上回答不出问题时，你在课后还会久久地感到烦恼吗?";
            queList[8] = "每到一个新地方，你是否常常会出现问题，如睡不好等。";
            queList[9] = "你明显偏食吗?";
            queList[10] = "你认为自己是个弱者吗?";
            queList[11] = "你觉得自己有些神经衰弱吗?";
            queList[12] = "看到苍蝇、蟑螂等讨厌的东西，你感到害怕吗?";
            queList[13] = "你常常因为想心事而躺在床上久久不能入睡吗?";
            queList[14] = "在人多的场合或陌生人面前说话，你是否感到窘迫?";
            queList[15] = "你受到的挫折与其他人相比，是否认为根本算不了什么?";
            queList[16] = "你是否喜欢冒险和刺激?";
            queList[17] = "你生活在使你感到快乐和温暖的班级里吗?";
            queList[18] = "你相信自己能够战胜任何挫折吗?";
            queList[19] = "你是否常常与同学们交流看法?";
            queList[20] = "你认为你的老师喜欢你吗?";
            queList[21] = "心情不愉快时，你的饭量与平时差不多吗?";
            queList[22] = "你是否每周至少进行一次喜欢的体育活动，如登山、打球等?";
            queList[23] = "即使在困难时，你还是相信困难终将过去吗?";
            queList[24] = "大部分时间你对未来充满信心吗?";
            queList[25] = "你有一个关心、爱护你的家吗?";
            queList[26] = "你是否有一些无话不谈的知心朋友?";
            queList[27] = "你认为自己健壮吗?";
            queList[28] = "生病时你依旧乐观吗?";
            queList[29] = "你是否认为家人需要你?";
            queSize = 30;
            // 问题中的选项
            optList[0][0] = "是";
            optList[0][1] = "否";
            optSize = 2;
        }
        else if(id == 13) { //自尊量表
            queList[0] = "我感到我是一个有价值的人，至少与其他人在同一水平上。";
            queList[1] = "我感到我有许多好的品质。";
            queList[2] = "归根结底，我倾向于觉得自己是一个失败者。";
            queList[3] = "我能像大多数人一样把事情做好。";
            queList[4] = "我感到自己值得自豪的地方不多。";
            queList[5] = "我对自己持肯定态度。";
            queList[6] = "总的来说，我对自己是满意的。";
            queList[7] = "我希望我能为自己赢得更多尊重。";
            queList[8] = "我确实时常感到自己毫无用处。";
            queList[9] = "我时常认为自己一无是处。";
            queSize = 10;
            // 问题中的选项
            optList[0][0] = "非常符合";
            optList[0][1] = "符合";
            optList[0][2] = "不符合";
            optList[0][3] = "很不符合";
            optSize = 4;
        }
        else if(id == 14) { //考试焦虑自测量表
            queList[0] = "在重要的考试前几天，我就坐立不安了";
            queList[1] = "临近考试时，我就拉肚子";
            queList[2] = "一想到考试即将来临，我的身体就会发僵";
            queList[3] = "在考试前，我总感到苦恼";
            queList[4] = "在考试前，我总感到烦躁，脾气变坏";
            queList[5] = "在紧张的复习期间，我常会想到：这次考试要是得到个低分数怎么办？";
            queList[6] = "越临近考试，我的注意力越难集中";
            queList[7] = "一想到马上就要考试了，我参加任何文娱活动都感到没劲";
            queList[8] = "考试前，我总预感到这次考试将要考砸";
            queList[9] = "考试前，我常做关于考试的梦";
            queList[10] = "到了考试那天，我就不安起来";
            queList[11] = "当我听到开始考试的铃声响了，我的心马上紧张得越跳越快";
            queList[12] = "遇到重要的考试，我的脑子就变得比平时迟钝";
            queList[13] = "看到考试题目越多、越难，我越感到不安";
            queList[14] = "在考试中，我的手会变得冰凉";
            queList[15] = "考试时，我感到十分紧张";
            queList[16] = "一遇到很难的考试，我就担心自己会不及格";
            queList[17] = "在紧张的考试中，我却会想些与考试无关的事情，注意力集中不起来";
            queList[18] = "考试时，我会紧张得连平时记得滚瓜烂熟的知识一点也回忆不起来";
            queList[19] = "在考试中，我会沉浸在空想之中，一时忘了自己是在考试";
            queList[20] = "考试中，我想上厕所的次数比平时多些";
            queList[21] = "考试中，即使不热，我也会浑身出汗";
            queList[22] = "在考试时，我紧张得手发僵，写字不流畅";
            queList[23] = "考试时，我经常会看错题目";
            queList[24] = "在进行重要考试时，我的头就会痛起来";
            queList[25] = "发现剩下的时间来不及做完全部考题，我就急得手足无措、浑身大汗";
            queList[26] = "如果我考了个低分数，家长或教师会严厉地指责我";
            queList[27] = "考试后，发现自己懂得的题没有答对时，就十分生自己的气";
            queList[28] = "有几次重要的考试之后，我都腹泻了";
            queList[29] = "我对考试十分厌烦";
            queList[30] = "只要考试不记成绩，我就会喜欢进行考试";
            queList[31] = "考试不应在像现在这样的紧张状态下进行";
            queList[32] = "要是不进行考试，我能学到更多的知识";
            queSize = 33;
            // 问题中的选项
            optList[0][0] = "很符合自己的情况";
            optList[0][1] = "比较符合自己的情况";
            optList[0][2] = "不太符合自己的情况";
            optList[0][3] = "很不符合自己的情况";
            optSize = 4;
        }
        else if(id == 15) { //学习风格调查问卷
            queList[0] = "为了较好地理解某些事物，我首先";
            queList[1] = "我办事喜欢";
            queList[2] = "当我回想以前做过的事，我的脑海中大多会出现";
            queList[3] = "我往往会";
            queList[4] = "在学习某些东西时, 我不禁会";
            queList[5] = "如何我是一名教师，我比较喜欢教";
            queList[6] = "我比较偏爱的获取新信息的媒体是";
            queList[7] = "一旦我了解了";
            queList[8] = "在学习小组中遇到难题时，我通常会";
            queList[9] = "我发现比较容易学习的是";
            queList[10] = "在阅读一本带有许多插图的书时，我一般会";
            queList[11] = "当我解决数学题时，我常常";
            queList[12] = "在我修课的班级中，";
            queList[13] = "在阅读非小说类作品时，我偏爱";
            queList[14] = "我喜欢的教师是";
            queList[15] = "当我在分析故事或小说时，";
            queList[16] = "当我做家庭作业时，我比较喜欢";
            queList[17] = "我比较喜欢";
            queList[18] = "我记得最牢是";
            queList[19] = "我特别喜欢教师";
            queList[20] = "我喜欢";
            queList[21] = "我更喜欢被认为是：";
            queList[22] = "当要我到一个新的地方去时，我喜欢";
            queList[23] = "我学习时";
            queList[24] = "我办事时喜欢";
            queList[25] = "当我阅读趣闻时, 我喜欢作者";
            queList[26] = "当我在上课时看到一幅图, 我通常会清晰地记着";
            queList[27] = "当我思考一大段信息资料时，我通常";
            queList[28] = "我最容易记住";
            queList[29] = "当我执行一项任务是，我喜欢";
            queList[30] = "当有人向我展示资料时，我喜欢";
            queList[31] = "当我写文章时，我通常";
            queList[32] = "当我必须参加小组合作课题时，我要";
            queList[33] = "当我要赞扬他人时，我说他是";
            queList[34] = "当我在聚会时与人见过面，我通常会记得";
            queList[35] = "当我学习新的科目时, 我喜欢";
            queList[36] = "我通常被他人认为是";
            queList[37] = "我喜欢的课程内容主要是";
            queList[38] = "在娱乐方面，我喜欢";
            queList[39] = "有些教师讲课时先给出一个提纲，这种提纲对我";
            queList[40] = "我认为只给合作的群体打一个分数的想法";
            queList[41] = "当我长时间地从事计算工作时";
            queList[42] = "我能画下我去过的地方";
            queList[43] = "当在小组中解决问题时，我更可能是";
            queSize = 44;
            // 问题中的选项
            optList[0][0] = "试试看";
            optList[0][1] = "深思熟虑";
            optList[1][0] = "讲究实际";
            optList[1][1] = "标新立异。";
            optList[2][0] = "一幅画面";
            optList[2][1] = "一些话语。";
            optList[3][0] = "明了事物的细节但不明其总体结构";
            optList[3][1] = "明了事物的总体结构但不明其细节";
            optList[4][0] = "谈论它";
            optList[4][1] = "思考它";
            optList[5][0] = "关于事实和实际情况的课程";
            optList[5][1] = "关于思想和理论方面的课程";
            optList[6][0] = "图画、图解、图形及图象";
            optList[6][1] = "书面指导和言语信息。";
            optList[7][0] = "事物的所有部分, 我就能把握其整体";
            optList[7][1] = "事物的整体，我就知道其构成部分";
            optList[8][0] = "挺身而出，畅所欲言";
            optList[8][1] = "往后退让，倾听意见";
            optList[9][0] = "事实性内容";
            optList[9][1] = "概念性内容";
            optList[10][0] = "仔细观察插图";
            optList[10][1] = "集中注意文字";
            optList[11][0] = "思考如何一步一步求解";
            optList[11][1] = "先看解答，然后设法得出解题步骤。";
            optList[12][0] = "我通常结识许多同学";
            optList[12][1] = "我认识的同学寥寥无几。";
            optList[13][0] = "那些能告诉我新事实和教我怎么做的东西";
            optList[13][1] = "那些能启发我思考的东西。";
            optList[14][0] = "在黑板上画许多图解的人";
            optList[14][1] = "花许多时间讲解的人。";
            optList[15][0] = "我想到各种情节并试图把他们结合起来去构想主题";
            optList[15][1] = "当我读完时只知道主题是什么，然后我得回头去寻找有关情节。";
            optList[16][0] = "一开始就立即做解答";
            optList[16][1] = "首先设法理解题意";
            optList[17][0] = "确定性的想法";
            optList[17][1] = "推论性的想法。";
            optList[18][0] = "看到的东西";
            optList[18][1] = "听到的东西。";
            optList[19][0] = "向我条理分明地呈示材料";
            optList[19][1] = "先给我一个概貌，再将材料与其他论题相联系";
            optList[20][0] = "在小组中学习";
            optList[20][1] = "独自学习";
            optList[21][0] = "对工作细节很仔细";
            optList[21][1] = "对工作很有创造力。";
            optList[22][0] = "要一幅地图";
            optList[22][1] = "要书面指南";
            optList[23][0] = "总是按部就班，我相信只要努力，终有所得";
            optList[23][1] = "我有时完全糊涂，然后恍然大悟。";
            optList[24][0] = "试试看";
            optList[24][1] = "想好再做";
            optList[25][0] = "以开门见山的方式叙述";
            optList[25][1] = "以新颖有趣的方式叙述。";
            optList[26][0] = "那幅图";
            optList[26][1] = "教师对那幅图的解说";
            optList[27][0] = "注意细节而忽视概貌";
            optList[27][1] = "先了解概貌而后深入细节";
            optList[28][0] = "我做过的事";
            optList[28][1] = "我想过的许多事。";
            optList[29][0] = "掌握一种方法";
            optList[29][1] = "想出多种方法";
            optList[30][0] = "图表";
            optList[30][1] = "概括其结果的文字";
            optList[31][0] = "先思考和着手写文章的开头，然后循序渐进";
            optList[31][1] = "先思考和写作文章的不同部分，然后加以整理。";
            optList[32][0] = "大家首先\"集思广益\"，人人贡献主意";
            optList[32][1] = "各人分头思考，然后集中起来比较各种想法。";
            optList[33][0] = "很敏感的";
            optList[33][1] = "想象力丰富的";
            optList[34][0] = "他们的模样";
            optList[34][1] = "他们的自我介绍";
            optList[35][0] = "全力以赴，尽量学得多学得好";
            optList[35][1] = "试图建立该科目与其他有关科目的联系";
            optList[36][0] = "外向的";
            optList[36][1] = "保守的";
            optList[37][0] = "具体材料（事实、数据）";
            optList[37][1] = "抽象材料 (概念、理论)";
            optList[38][0] = "看电视";
            optList[38][1] = "看书";
            optList[39][0] = "有所帮助";
            optList[39][1] = "很有帮助";
            optList[40][0] = "吸引我";
            optList[40][1] = "不吸引我";
            optList[41][0] = "我喜欢重复我的步骤并仔细地检查我的工作";
            optList[41][1] = "我认为检查工作非常无聊，我是在逼迫自己这么干";
            optList[42][0] = "很容易且相当精确";
            optList[42][1] = "很困难且没有许多细节";
            optList[43][0] = "思考解决问题的步骤";
            optList[43][1] = "思考可能的结果及其在更广泛的领域内的应用";
            optSize = 2;
        }

        Button button = (Button) view.findViewById(R.id.test_start); //开始测试按钮
        int finalQueBg = queBg;
        int finalQueSize = queSize;
        int finalOptSize = optSize;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = getArguments();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                TestProFragment testProFragment = new TestProFragment();

                b.putInt("test_category",test_category);
                int[] selected_opt = new int[55]; //记录用户的选项
                b.putSerializable("selected_opt",selected_opt);
                b.putSerializable("que_list",queList);
                b.putInt("que_size", finalQueSize); //问题数量
                b.putSerializable("opt_list",optList);
                b.putInt("opt_size", finalOptSize); //选项数量
                b.putInt("test_id",id); //传入测试编号
                b.putInt("que_id",0); //传入问题编号
                b.putInt("que_bg", finalQueBg); //传入背景图片
                testProFragment.setArguments(b);

                transaction.replace(R.id.test_layout, testProFragment); //替换fragment为测试过程页
                transaction.commitNow();
            }
        });
        return view;
    }
}