package com.example.study;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class bookshelfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        ImageButton mbtn_bookshelf01 = (ImageButton) findViewById(R.id.btn01);
        mbtn_bookshelf01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf02 = (ImageButton) findViewById(R.id.btn02);
        mbtn_bookshelf02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf03 = (ImageButton) findViewById(R.id.btn03);
        mbtn_bookshelf03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf04 = (ImageButton) findViewById(R.id.btn04);
        mbtn_bookshelf04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf05 = (ImageButton) findViewById(R.id.btn05);
        mbtn_bookshelf05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf06 = (ImageButton) findViewById(R.id.btn06);
        mbtn_bookshelf06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf07 = (ImageButton) findViewById(R.id.btn07);
        mbtn_bookshelf07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf08 = (ImageButton) findViewById(R.id.btn08);
        mbtn_bookshelf08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf09 = (ImageButton) findViewById(R.id.btn09);
        mbtn_bookshelf09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf10 = (ImageButton) findViewById(R.id.btn10);
        mbtn_bookshelf10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf11 = (ImageButton) findViewById(R.id.btn11);
        mbtn_bookshelf11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf12 = (ImageButton) findViewById(R.id.btn12);
        mbtn_bookshelf12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf13 = (ImageButton) findViewById(R.id.btn13);
        mbtn_bookshelf13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf14 = (ImageButton) findViewById(R.id.btn14);
        mbtn_bookshelf14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf15 = (ImageButton) findViewById(R.id.btn15);
        mbtn_bookshelf15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf16 = (ImageButton) findViewById(R.id.btn16);
        mbtn_bookshelf16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf17 = (ImageButton) findViewById(R.id.btn17);
        mbtn_bookshelf17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf18 = (ImageButton) findViewById(R.id.btn18);
        mbtn_bookshelf18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf19 = (ImageButton) findViewById(R.id.btn19);
        mbtn_bookshelf19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf20 = (ImageButton) findViewById(R.id.btn20);
        mbtn_bookshelf20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf21 = (ImageButton) findViewById(R.id.btn21);
        mbtn_bookshelf21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf22 = (ImageButton) findViewById(R.id.btn22);
        mbtn_bookshelf22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf23 = (ImageButton) findViewById(R.id.btn23);
        mbtn_bookshelf23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf24 = (ImageButton) findViewById(R.id.btn24);
        mbtn_bookshelf24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
        ImageButton mbtn_bookshelf25 = (ImageButton) findViewById(R.id.btn25);
        mbtn_bookshelf25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(bookshelfActivity.this).create();
                alert.setTitle("兑换？");             //设置弹出标题
                alert.setMessage("此兑换需要10滴墨水");//设置弹出内容
                //添加按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//返回主界面(之后改为减少墨水）
                    }
                });
                alert.show();
            }

            ;
        });
    }
}
