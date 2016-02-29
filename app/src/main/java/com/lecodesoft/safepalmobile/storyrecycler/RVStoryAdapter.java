package com.lecodesoft.safepalmobile.storyrecycler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.lecodesoft.safepalmobile.R;
import com.lecodesoft.safepalmobile.SingleStoryMoreDetails.SingleStoryMoreDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/16/15.
 */
public class RVStoryAdapter extends RecyclerView.Adapter<RVStoryAdapter.StoryViewHolder> implements Filterable {

    //creation of image list letters
    String letterforthumbnail;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    List<Story> stories;
    private static int selecteditem = -1;
    private List<Story> orig;


    public static class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Intent singleStoryIntent;
        CardView cvStory;
        ImageView ssActionTakenThumbnail;

        TextView ssLocation;
        TextView ssPostTime;
        TextView ssIncidentDescription;
        TextView ssIncidentHappenedTime;
        TextView ssIncidentStatus;
        TextView ssIncidentType;


        public StoryViewHolder(View itemView) {
            super(itemView);
            //for every single item that is clicked

            itemView.setOnClickListener(this);
            cvStory = (CardView) itemView.findViewById(R.id.single_story_card_view);
            ssActionTakenThumbnail = (ImageView) itemView.findViewById(R.id.iv_single_story_actiontaken);
            ssLocation = (TextView) itemView.findViewById(R.id.tv_single_story_incident_location);
            ssPostTime = (TextView) itemView.findViewById(R.id.tv_single_story_incident_post_time);
            ssIncidentDescription = (TextView) itemView.findViewById(R.id.tv_single_story_incident_description);
            ssIncidentHappenedTime = (TextView) itemView.findViewById(R.id.tv_single_story_incident_happened_time);
            ssIncidentStatus = (TextView) itemView.findViewById(R.id.tv_single_story_incident_status);
            ssIncidentType = (TextView) itemView.findViewById(R.id.tv_single_story_incident_type);
        }

        @Override
        public void onClick(View v) {
            /**
             * This click is responsible for on click item
             * */
           singleStoryIntent = new Intent(v.getContext(), SingleStoryMoreDetails.class);

            singleStoryIntent.putExtra("sendSSLocation", ssLocation.getText());
            singleStoryIntent.putExtra("sendSSPostTime", ssPostTime.getText());
            singleStoryIntent.putExtra("sendSSIncidentDescription", ssIncidentDescription.getText());
            singleStoryIntent.putExtra("sendSSIncidentHappenedTime", ssIncidentHappenedTime.getText());
            singleStoryIntent.putExtra("sendSSIncidentStatus", ssIncidentStatus.getText());
            singleStoryIntent.putExtra("sendSSIncidentType", ssIncidentType.getText());

            //convert image to bitmap so that i send it to another activity
            ssActionTakenThumbnail.buildDrawingCache();
            Bitmap bitmap = ssActionTakenThumbnail.getDrawingCache();

            singleStoryIntent.putExtra("sendSSIncidentBitmapImage", bitmap);

            v.getContext().startActivity(singleStoryIntent);

        }
    }// end of inner class


    public RVStoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    public void setSelecteditem(int pos) {
        selecteditem = pos;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_rv_story_card, viewGroup, false);
        StoryViewHolder svh = new StoryViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder storyViewHolder, int i) {
        storyViewHolder.ssIncidentStatus.setText(stories.get(i).singleStoryIncidentStatus);
        storyViewHolder.ssIncidentDescription.setText(stories.get(i).singleStoryIncidentDescription);
        storyViewHolder.ssPostTime.setText(stories.get(i).singleStoryPostTime);
        storyViewHolder.ssLocation.setText(stories.get(i).singleStoryIncidentLocation);

        storyViewHolder.ssIncidentHappenedTime.setText(stories.get(i).singleStoryIncidentHappenedTime);
        storyViewHolder.ssIncidentType.setText(stories.get(i).singleStoryIncidentType);

        /***setting letters for actiontakenthumbnail*/

        switch (String.valueOf(stories.get(i).singleStoryIncidentType)){

            case "Sexual Assault (includes attempted rape and all sexual violence/abuse without penetration)":
                letterforthumbnail="SA";
             break ;
            case "Physical Assault (includes hitting, slapping, kicking, shoving, etc. that are not sexual in nature)":
                letterforthumbnail="PA";
              break;
            case "Forced Marriage (includes early marriage)":
                letterforthumbnail="FM";
                break;
            case "Denial of Resources, opportunities and services":
                letterforthumbnail="DR";
                break;
            case "Psychological Abuse":
                letterforthumbnail="P";
                break;
            case "Female Genital Cutting / Mutilation":
                letterforthumbnail="FGC";
                break;

            case "Other GBV (specify)":
                letterforthumbnail="FGC";
                break;
            default:
                letterforthumbnail="SP";
                break;
        }


        // Create a new TextDrawable for our image's background
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letterforthumbnail, generator.getRandomColor());

        storyViewHolder.ssActionTakenThumbnail.setImageDrawable(drawable);



        //check for selected item
        if (selecteditem == i)
            storyViewHolder.itemView.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Story> results = new ArrayList<Story>();
                if (orig == null)
                    orig = stories;
                if (constraint != null) {
                    if (orig != null & orig.size() > 0) {
                        for (final Story g : orig) {
                            if (g.getSingleStoryIncidentDescription().toLowerCase().contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                stories = (ArrayList<Story>) results.values;
                notifyDataSetChanged();

            }
        };

    }
}
