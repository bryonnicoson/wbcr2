package com.bryonnicoson.wbcr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bryonnicoson.wbcr.model.Dog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DogListAdapter extends ArrayAdapter<Dog> {

    static class ViewHolder {
        ImageView ivImage;
        TextView tvName;
        TextView tvSize;
        TextView tvAge;
//        ImageView ivSex;
        TextView tvSex;
        TextView tvBreed;
        TextView tvOptions;
        ImageView ivCheck;
    }

    public DogListAdapter(Context context, ArrayList<Dog> dogs) {
        super(context, 0, dogs);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Dog dog = getItem(position);

        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.dog_card, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.ivImage = (ImageView) view.findViewById(R.id.dog_photo);
            viewHolder.tvName = (TextView) view.findViewById(R.id.dog_name);
            viewHolder.tvSize = (TextView) view.findViewById(R.id.dog_size);
            viewHolder.tvAge = (TextView) view.findViewById(R.id.dog_age);
            viewHolder.tvSex = (TextView) view.findViewById(R.id.dog_sex);
//            viewHolder.ivSex = (ImageView) view.findViewById(R.id.dog_sex);
            viewHolder.tvBreed = (TextView) view.findViewById(R.id.dog_breed);
            viewHolder.ivCheck = (ImageView) view.findViewById(R.id.dog_check);
            viewHolder.tvOptions = (TextView) view.findViewById(R.id.dog_options);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(getContext()).load(dog.images.get(0)).placeholder(R.drawable.placeholder).resize(200,200).into(viewHolder.ivImage);
        viewHolder.tvName.setText(dog.name);
        viewHolder.tvSize.setText(dog.size);
        viewHolder.tvAge.setText(dog.age);
        viewHolder.tvSex.setText(dog.sex);
        viewHolder.tvBreed.setText(dog.breed);

        // if hasShots, housetrained, altered, add imageview checkedbox and textview string
        if ((dog.hasShots)||(dog.altered)||(dog.housetrained)) {
            viewHolder.ivCheck.setImageResource(R.drawable.checked36);
        }
        StringBuilder sb = new StringBuilder();
        if (dog.hasShots){
            sb.append("vaccinated");
        }
        if (dog.altered){
            if(!sb.equals("")){
                sb.append(" \u00b7 ");
            }
            if(dog.sex == "Male"){
                sb.append("neutered");
            } else {
                sb.append("spayed");
            }
        }
        if (dog.housetrained){
            if(!sb.equals("")){
                sb.append(" \u00b7 ");  // add middot between options
            }
            sb.append("house-trained");
        }

        viewHolder.tvOptions.setText(sb.toString());

//        if (dog.sex.equals("Male"))
//            viewHolder.ivSex.setImageResource(R.drawable.male);
//        else
//            viewHolder.ivSex.setImageResource(R.drawable.female);

        return view;
    }
}
