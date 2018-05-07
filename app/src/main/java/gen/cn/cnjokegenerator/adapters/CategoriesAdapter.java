package gen.cn.cnjokegenerator.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import gen.cn.cnjokegenerator.R;
import gen.cn.cnjokegenerator.databinding.CheckedItemBinding;
import gen.cn.cnjokegenerator.models.SelectableCategory;


public class CategoriesAdapter extends RecyclerView.Adapter {

    private final List<SelectableCategory> mValues;
    CategoriesAdapter.OnItemSelectedListener listener;


    public CategoriesAdapter(CategoriesAdapter.OnItemSelectedListener listener,
                             List<String> categories) {
        this.listener = listener;
        mValues = new ArrayList<>();
        for (String category : categories) {
            mValues.add(new SelectableCategory(category, false));
        }
    }

    @Override
    public SelectableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CheckedItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.checked_item,
                parent, false);

        return new SelectableViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        SelectableViewHolder holder = (SelectableViewHolder) viewHolder;
        SelectableCategory selectableItem = mValues.get(position);
        holder.binding.setCategory(selectableItem);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public ArrayList<String> getSelectedItems() {
        ArrayList<String> selectedItems = new ArrayList<>();
        for (SelectableCategory item : mValues) {
            if (item.isSelected()) {
                selectedItems.add(item.getTitle());
            }
        }
        return selectedItems;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public interface OnItemSelectedListener {

        void onItemSelected(SelectableCategory item);
    }
}


class SelectableViewHolder extends RecyclerView.ViewHolder {

    CategoriesAdapter.OnItemSelectedListener itemSelectedListener;
    CheckedItemBinding binding;

    public SelectableViewHolder(CheckedItemBinding binding, CategoriesAdapter.OnItemSelectedListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        itemSelectedListener = listener;
        binding.checkedTextItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectableViewHolder.this.binding.getCategory() != null)
                    SelectableViewHolder.this.binding.getCategory().setSelected(!SelectableViewHolder.this.binding.getCategory().isSelected());
                if (itemSelectedListener != null)
                    itemSelectedListener.onItemSelected(SelectableViewHolder.this.binding.getCategory());
            }
        });

    }




}
