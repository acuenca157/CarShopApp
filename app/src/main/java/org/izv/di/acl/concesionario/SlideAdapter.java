package org.izv.di.acl.concesionario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SlideAdapter extends SliderViewAdapter<SlideAdapter.Holder> {

    String[] images;
    Context ctx;

    public SlideAdapter(String[] images, Context ctx){
        this.images = images;
        this.ctx = ctx;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.slider_item, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        Glide.with(ctx).load(images[i]).into(holder.imageView);
    }

    @Override
    public int getCount() {
        if (images == null)
            return 0;
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
