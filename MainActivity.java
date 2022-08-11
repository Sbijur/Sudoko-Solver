package com.example.sud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int [][]a=new int [9][9];
    int [][]b=new int [9][9];
    int bpress=-1;
    int xp,yp;
    int zero=0;
    TextView t;
    GridLayout g;
    boolean st=false;
    boolean sc=true;
    boolean checker=false;
    boolean pressen=false;
    int errx,erry,err;
    TextView errt;
    public void input()
    {
        int p,q,x;
        g=(GridLayout) findViewById(R.id.grid);
        for(int i=0;i<g.getChildCount();i++)
        {
            TextView t=(TextView)((GridLayout )g).getChildAt(i);
            t.setTextColor(Color.BLACK);
            errt=(TextView) t;
            x=Integer.valueOf((String)g.getChildAt(i).getTag());
            p=x/9;
            q=x%9;
            if(t.getText().toString().equals(""))
            {
                a[p][q]=0;
                zero++;
            }
            else {
                a[p][q]= Integer.parseInt(t.getText().toString());
            }
            b[p][q]=a[p][q];
        }
        if(check()==1)
        {
            checker=true;
            if(solve()==0)
            {

            }
        }
        else
        {
            t.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    t.setTextColor(Color.BLACK);
                    errt.setTextColor(Color.BLACK);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            errt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    errt.setTextColor(Color.BLACK);
                    t.setTextColor(Color.BLACK);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }
    public int check()
    {
        g=(GridLayout) findViewById(R.id.grid);
        int p,q,x,y;
        for(int i=0;i<g.getChildCount();i++)
        {
            t=(TextView)((GridLayout )g).getChildAt(i);
            x=Integer.valueOf((String)g.getChildAt(i).getTag());
            p=x/9;
            q=x%9;
            if(a[p][q]!=0)
            {
                if((block(a[p][q],p,q)>=2)||(row(a[p][q],p)>=2)||(col(a[p][q],q)>=2))
                {
                    t.setTextColor(Color.RED);
                    err=Integer.parseInt(t.getText().toString());
                    for(int j=0;j<g.getChildCount();j++)
                    {
                        y=Integer.valueOf((String)g.getChildAt(j).getTag());
                        if((errx==y/9)&&(erry==y%9))
                        {
                            errt=(TextView)((GridLayout )g).getChildAt(j);
                            errt.setTextColor(Color.RED);
                        }
                    }
                    checker=false;
                    return 0;
                }
            }

        }
        return 1;
    }
    public void onPress(View view)
    {
        g=(GridLayout) findViewById(R.id.grid);
        t=(TextView) findViewById(R.id.options);
        input();
        System.out.print("Hello"+checker);
        if(pressen==false)
        {
            Toast.makeText(MainActivity.this, "Press Enter Numbers", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if((zero!=81)&&(checker==true))
            {
                t.setAlpha(1f);
                st=true;
            }
            if(zero==81)
            {
                Toast.makeText(MainActivity.this, "EMPTY!!", Toast.LENGTH_SHORT).show();
                t.setAlpha(0.3f);
                st=false;
            }
            zero=0;
            pressen=false;
        }

        if(st==true)
        {
            t=(TextView) findViewById(R.id.options);
            t.setVisibility(View.INVISIBLE);
            t=(TextView) findViewById(R.id.mainclear);
            t.setVisibility(View.INVISIBLE);
            t=(TextView) findViewById(R.id.number);
            t.setVisibility(View.INVISIBLE);
            t=(TextView) findViewById(R.id.backb);
            t.setVisibility(View.INVISIBLE);
            t=(TextView) findViewById(R.id.full);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.row);
            t.setAlpha(0.3f);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.col);
            t.setAlpha(0.3f);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.cell);
            t.setAlpha(0.3f);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.num);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.edit);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.back);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.clear);
            t.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Options", Toast.LENGTH_SHORT).show();
            editnum();
        }
    }
    public void ennum(View view)
    {
        input();
        if((zero!=81)&&(checker==true))
        {
            pressen=true;
            t=(TextView) findViewById(R.id.options);
            t.setAlpha(1f);
        }
        if(zero==81)
        {
            Toast.makeText(MainActivity.this, "EMPTY!!", Toast.LENGTH_SHORT).show();
            t.setAlpha(0.3f);
        }
        zero=0;

    }
    public void dis(View view)
    {
        g=(GridLayout) findViewById(R.id.grid);
        int x,p,q;
        for(int i=0;i<g.getChildCount();i++)
        {
            TextView t=(TextView)((GridLayout )g).getChildAt(i);
            x=Integer.valueOf((String)g.getChildAt(i).getTag());
            p=x/9;
            q=x%9;
            t.setText(String.valueOf(a[p][q]));

        }
    }
    public void clearfunc(View view)
    {
        g=(GridLayout) findViewById(R.id.grid);
        int p,q,tag;
        for(int i=0;i<g.getChildCount();i++)
        {
            TextView tex=(TextView)((GridLayout )g).getChildAt(i);
            tag=Integer.valueOf((String)g.getChildAt(i).getTag());
            q=tag%9;
            p=tag/9;
            if(b[p][q]!=0)
            {
                tex.setText(String.valueOf(b[p][q]));
            }
            else
            {
                tex.setText("");
            }
        }
    }
    public void btag(View view)
    {
        if(sc==false)
        {
            String s;
            g=(GridLayout) findViewById(R.id.grid);
            for(int i=0;i<g.getChildCount();i++)
            {
                TextView textView=(TextView)((GridLayout )g).getChildAt(i);
                textView.setTextColor(Color.BLACK);
            }
            bpress=Integer.valueOf((String) view.getTag());
            TextView text=(TextView) view;
            text.setTextColor(Color.BLUE);
            text=(TextView) findViewById(R.id.cellstat);
            text.setTextColor(Color.GRAY);
            text.setVisibility(View.VISIBLE);
            if(bpress==-1)
            {
                s="Cell Selected:None";

            }
            else
            {
                s="Cell Selected:"+Integer.toString(bpress+1)+"     \tRow:"+Integer.toString((bpress/9)+1)+"     \tColumn:"+Integer.toString((bpress%9)+1);
                Button b=(Button) findViewById(R.id.row);
                b.setAlpha(1f);
                b=(Button) findViewById(R.id.col);
                b.setAlpha(1f);
                b=(Button) findViewById(R.id.cell);
                b.setAlpha(1f);

            }
            text.setText(s);
        }
    }
    public void rowfunc(View view)
    {
        g=(GridLayout) findViewById(R.id.grid);
        int p,tag,q;
        int x=bpress/9;
        for(int i=0;i<g.getChildCount();i++)
        {
            TextView t=(TextView)((GridLayout )g).getChildAt(i);
            tag=Integer.valueOf((String)g.getChildAt(i).getTag());
            q=tag%9;
            p=tag/9;
            if(x==p)
            {
                t.setText(String.valueOf(a[p][q]));
            }
        }
    }
    public void colfunc(View view)
    {
        g=(GridLayout) findViewById(R.id.grid);
        int p,tag,q;
        int x=bpress%9;
        for(int i=0;i<g.getChildCount();i++)
        {
            TextView t=(TextView)((GridLayout )g).getChildAt(i);
            tag=Integer.valueOf((String)g.getChildAt(i).getTag());
            q=tag%9;
            p=tag/9;
            if(x==q)
            {
                t.setText(String.valueOf(a[p][q]));
            }
        }
    }
    public void cellfunc(View view)
    {
        int p,q;
        p=bpress/9;
        q=bpress%9;
        g=(GridLayout)findViewById(R.id.grid);
        for(int i=0;i<g.getChildCount();i++)
        {
            if(bpress==Integer.valueOf((String)g.getChildAt(i).getTag()))
            {
                TextView text=(TextView)((GridLayout )g).getChildAt(i);
                text.setText(String.valueOf(a[p][q]));
            }
        }
    }
    public void numfunc(View view)
    {
        int x=Integer.valueOf((String) view.getTag());
        g=(GridLayout) findViewById(R.id.numgrid);
        g.setVisibility(View.GONE);
        g=(GridLayout) findViewById(R.id.grid);
        g.setVisibility(View.VISIBLE);
        int p,q,tag;
        for(int i=0;i<g.getChildCount();i++) {
            TextView t = (TextView) ((GridLayout) g).getChildAt(i);
            tag = Integer.valueOf((String) g.getChildAt(i).getTag());
            q = tag % 9;
            p = tag / 9;
            if (x == a[p][q]) {
                t.setText(String.valueOf(a[p][q]));
            }
        }

    }
    public void exit(View view)
    {
        g=(GridLayout) findViewById(R.id.numgrid);
        g.setVisibility(View.GONE);
        g=(GridLayout) findViewById(R.id.grid);
        g.setVisibility(View.VISIBLE);
    }
    public void display(View view)
    {
        g=(GridLayout) findViewById(R.id.grid);
        g.setVisibility(View.GONE);
        g=(GridLayout) findViewById(R.id.numgrid);
        g.setVisibility(View.VISIBLE);
    }
    public int solve()
    {

        int r,c,x,y,z;
        if(emp()==1)
        {
            return 0;
        }
        r=xp;
        c=yp;
        for(int i=1;i<=9;i++)
        {
            x=block(i,r,c);
            y=col(i,c);
            z=row(i,r);
            if((x==0)&&(y==0)&&(z==0))
            {
                a[r][c]=i;
                if(solve()==0)
                {
                    return 0;
                }
            }
            a[r][c]=0;
        }
        return 1;

    }

    public int block(int v,int x,int y)
    {
        int m=0,n=0,p=0,q=0,c=0;
        if((0<=x)&&(x<=2))
        {
            p=0;
            q=2;
        }
        else if((3<=x)&&(x<=5))
        {
            p=3;
            q=5;
        }
        else if((6<=x)&&(x<=8))
        {
            p=6;
            q=8;
        }

        if((0<=y)&&(y<=2))
        {
            m=0;
            n=2;
        }
        else if((3<=y)&&(y<=5))
        {
            m=3;
            n=5;
        }
        else if((6<=y)&&(y<=8))
        {
            m=6;
            n=8;
        }

        for(int i=p;i<=q;i++)
        {
            for(int j=m;j<=n;j++)
            {
                if(v==a[i][j])
                {
                    errx=i;
                    erry=j;
                    c++;
                }
            }
        }
        return(c);

    }
    public int col(int x,int j)
    {
        int c=0;
        for(int i=0;i<9;i++)
        {

            if(a[i][j]==x)
            {
                errx=i;
                erry=j;
                c++;
            }
        }
        return(c);
    }
    public int row(int x,int i)
    {
        int c=0;
        for(int j=0;j<9;j++)
        {
            if(a[i][j]==x)
            {
                errx=i;
                erry=j;
                c++;
            }
        }
        return(c);
    }
    public int emp()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(a[i][j]==0)
                {
                    xp=i;
                    yp=j;
                    return 0;
                }
            }
        }
        return 1;
    }
    public void backbutton(View view)
    {
        fullclear(null);
        g=(GridLayout) findViewById(R.id.grid);
        t=(TextView) findViewById(R.id.options);
        t.setAlpha(0.3f);
        bpress=-1;
        st=false;
        t.setVisibility(View.VISIBLE);
        t=(TextView) findViewById(R.id.edit);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.cellstat);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.mainclear);
        t.setVisibility(View.VISIBLE);
        t=(TextView) findViewById(R.id.number);
        t.setVisibility(View.VISIBLE);
        t=(TextView) findViewById(R.id.options);
        t.setVisibility(View.VISIBLE);
        t=(TextView) findViewById(R.id.cellstat);
        t.setVisibility(View.INVISIBLE);
        func1();
        editnum();

    }
    public void func1()
    {
        t=(TextView) findViewById(R.id.row);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.col);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.cell);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.num);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.edit);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.back);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.clear);
        t.setVisibility(View.INVISIBLE);
        t=(TextView) findViewById(R.id.full);
        t.setVisibility(View.INVISIBLE);
    }
    public void fullclear(View view)
    {
        int x,p,q;
        g=(GridLayout) findViewById(R.id.grid);
        for(int i=0;i<g.getChildCount();i++)
        {
            TextView t=(TextView)((GridLayout )g).getChildAt(i);
            x=Integer.valueOf((String)g.getChildAt(i).getTag());
            p=x/9;
            q=x%9;
            a[p][q]=0;
            b[p][q]=0;
            t.setText("");
        }
        TextView t=(TextView) findViewById(R.id.options);
        t.setAlpha(0.3f);
    }
    public void backbfunc(View view)
    {
        input();
        if((zero!=81)&&(checker==true))
        {
            editnum();
            t=(TextView) findViewById(R.id.backb);
            t.setVisibility(View.INVISIBLE);
            t=(TextView) findViewById(R.id.full);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.row);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.col);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.cell);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.num);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.edit);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.back);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.clear);
            t.setVisibility(View.VISIBLE);
            t=(TextView) findViewById(R.id.cellstat);
            t.setVisibility(View.INVISIBLE);
        }
        if(zero==81)
        {
            Toast.makeText(MainActivity.this, "EMPTY!!", Toast.LENGTH_SHORT).show();
        }
        zero=0;

    }
    public void editnum()
    {
        g=(GridLayout) findViewById(R.id.grid);
        for(int i=0;i<g.getChildCount();i++)
        {
            TextView t=(TextView)((GridLayout )g).getChildAt(i);
            int x=Integer.valueOf((String)g.getChildAt(i).getTag());
            System.out.println(x);
            if(sc==true)
            {
                t.setFocusable(false);
            }
            else
            {
                t.setFocusableInTouchMode(true);
            }
        }
        System.out.println(sc);
        sc=!sc;
    }
    public void editfunc(View view)
    {
        editnum();
        func1();
        t=(TextView) findViewById(R.id.backb);
        t.setVisibility(View.VISIBLE);
        t=(TextView) findViewById(R.id.cellstat);
        t.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}