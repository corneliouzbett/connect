package com.lavacreators.corneliouzbett.ufarm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lavacreators.corneliouzbett.ufarm.R;
import com.lavacreators.corneliouzbett.ufarm.activities.HistoryActivity;
import com.lavacreators.corneliouzbett.ufarm.activities.RequestActivity;
import com.lavacreators.corneliouzbett.ufarm.activities.constants.Menus;
import com.lavacreators.corneliouzbett.ufarm.model.History;
import com.lavacreators.corneliouzbett.ufarm.model.Menu;

import java.util.ArrayList;

import static com.lavacreators.corneliouzbett.ufarm.activities.constants.Menus.HISTORY;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.viewHolder> {
    private ArrayList<Menu> menuArrayList;
    private Context context;

    public MenuRecyclerAdapter(ArrayList<Menu> menuArrayList, Context context) {
        this.menuArrayList = menuArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_dashboard,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerAdapter.viewHolder viewHolder, int i) {
        viewHolder.titleTextview.setText(menuArrayList.get(i).getTitle());
        viewHolder.IconimageView.setImageDrawable(menuArrayList.get(i).getDrawable());
        viewHolder.IconimageView.setBackground(menuArrayList.get(i).getColor());
        viewHolder.menuCardView.setOnClickListener( menu ->{
            switch (menuArrayList.get(i).getTitle()){

                case Menus.HISTORY:
                    context.startActivity(new Intent(context, HistoryActivity.class));
                    break;
                case Menus.REQUEST:
                    context.startActivity(new Intent(context, RequestActivity.class));
                    break;
                    default:
                        Toast.makeText(context,"Invalid selection",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        ImageView IconimageView;
        TextView titleTextview;
        CardView menuCardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            IconimageView = itemView.findViewById(R.id.menuIcon_imageview);
            titleTextview = itemView.findViewById(R.id.menu_title_tv);
            menuCardView = itemView.findViewById(R.id.menu_cardview);
        }
    }
}
