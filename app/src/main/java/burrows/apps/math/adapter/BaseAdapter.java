package burrows.apps.math.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Application utilities.
 *
 * @author <a href="mailto:jared.burrows@ngc.com">Jared Burrows</a>
 * @since 0.0.1
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> model = new ArrayList<>();
    protected SparseBooleanArray selectedItems = new SparseBooleanArray();

    @Override
    public int getItemCount() {
        return model.size();
    }

    public List<T> getList() {
        return this.model;
    }

    public T getItem(final int position) {
        return this.model.get(position);
    }

    public int getPosition(final T item) {
        return this.model.indexOf(item);
    }

    public void clear() {
        int size = this.model.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.model.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void add(final T app) {
        this.model.add(app);
        this.notifyItemInserted(this.model.size() + 1);
    }

    public void add(final List<T> model) {
        this.model.addAll(model);
        this.notifyItemRangeInserted(0, model.size() + 1);
    }

    public void add(final int index, final T app) {
        this.model.add(index, app);
        this.notifyItemInserted(index);
    }

    public void set(final int index, final T object) {
        this.model.set(index, object);
        this.notifyDataSetChanged();
    }

//    public void set(final int index, final T object) {
//        this.model.set(index, object);
//        this.notifyItemInserted(index);
//    }

    public void remove(final int position, final T app) {
        this.model.remove(app);
        this.notifyItemRangeRemoved(position, this.model.size());
    }

    public void remove(final T object) {
        final int position = getPosition(object);
        this.model.remove(object);
        this.notifyItemRemoved(position);
    }

    public void remove(final int position) {
        this.model.remove(position);
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, this.model.size());
    }

    public void sort(final Comparator<? super T> comparator) {
        Collections.sort(this.model, comparator);
        this.notifyItemRangeChanged(0, this.getItemCount());
    }

    /**
     * Return the number of selected items.
     *
     * @return Number of selected items.
     */
    public int getItemSelectedCount() {
        return this.selectedItems.size();
    }

    /**
     * Return all selected IDs.
     *
     * @return Selected IDs.
     */
    public SparseBooleanArray getSelectedItems() {
        return this.selectedItems;
    }

    /**
     * Return all selected items.
     *
     * @return Selected IDs.
     */
    public List<T> getSelectedList() {
        List<T> list = new ArrayList<>();
        SparseBooleanArray sparseBooleanArray = this.getSelectedItems();
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            T model = this.getList().get(sparseBooleanArray.keyAt(i));
            list.add(model);
        }
        return list;
    }

    /**
     * Remove all current selections.
     */
    public void removeSelections() {
        this.selectedItems.clear();
        this.notifyDataSetChanged();
    }

    /**
     * Toggle selection of item in ListView.
     *
     * @param position Position of view in ListView.
     */
    public void toggleSelection(final int position) {
        this.selectItem(position, !this.selectedItems.get(position));
    }

    /**
     * Change the current view state to selected.
     *
     * @param position Position of view in ListView.
     * @param value    True if view is selected.
     */
    public void selectItem(final int position, final boolean value) {
        if (value) {
            this.selectedItems.put(position, true);
        } else {
            this.selectedItems.delete(position);
        }

        this.notifyItemChanged(position);
    }
}
